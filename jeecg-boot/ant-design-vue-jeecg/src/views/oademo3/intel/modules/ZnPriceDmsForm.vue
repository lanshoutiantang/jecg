<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="门店编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopid']" placeholder="请输入门店编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品类别（小类）" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlid']" placeholder="请输入商品类别（小类）"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="定价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['normalprice']" placeholder="请输入定价" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="价格段下限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['band1']" placeholder="请输入价格段下限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="价格段上限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['band2']" placeholder="请输入价格段上限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="折扣下限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['disc1']" placeholder="请输入折扣下限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="折扣上限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['disc2']" placeholder="请输入折扣上限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="实际售价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['trueprice']" placeholder="请输入实际售价" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="实际售价对应的日均销量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dms']" placeholder="请输入实际售价对应的日均销量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="更新或新增日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sdate']" placeholder="请输入更新或新增日期"></a-input>
            </a-form-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'

  export default {
    name: 'ZnPriceDmsForm',
    components: {
      JFormContainer,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        form: this.$form.createForm(this),
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/intel/znPriceDms/add",
          edit: "/intel/znPriceDms/edit",
          queryById: "/intel/znPriceDms/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'shopid','xlid','goodsid','normalprice','band1','band2','disc1','disc2','trueprice','dms','sdate'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'shopid','xlid','goodsid','normalprice','band1','band2','disc1','disc2','trueprice','dms','sdate'))
      },
    }
  }
</script>