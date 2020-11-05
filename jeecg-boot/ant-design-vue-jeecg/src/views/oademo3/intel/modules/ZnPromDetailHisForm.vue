<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sysOrgCode']" placeholder="请输入所属部门"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="促销计划编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planid']" placeholder="请输入促销计划编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="促销计划名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planname']" placeholder="请输入促销计划名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="促销开始日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择促销开始日期时间" v-decorator="['begindate']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="促销结束日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择促销结束日期时间" v-decorator="['enddate']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="门店编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopid']" placeholder="请输入门店编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="促销售价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['price']" placeholder="请输入促销售价" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="采购分货数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['planqty']" placeholder="请输入采购分货数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['flag']" placeholder="请输入补货标识"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="备份日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择备份日期" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>
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
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'ZnPromDetailHisForm',
    components: {
      JFormContainer,
      JDate,
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
          add: "/intel/znPromDetailHis/add",
          edit: "/intel/znPromDetailHis/edit",
          queryById: "/intel/znPromDetailHis/queryById"
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
          this.form.setFieldsValue(pick(this.model,'sysOrgCode','planid','planname','begindate','enddate','shopid','goodsid','price','planqty','flag','sdate'))
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
        this.form.setFieldsValue(pick(row,'sysOrgCode','planid','planname','begindate','enddate','shopid','goodsid','price','planqty','flag','sdate'))
      },
    }
  }
</script>