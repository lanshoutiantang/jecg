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
            <a-form-item label="小类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlid']" placeholder="请输入小类编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="折扣比例下限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['disc1']" placeholder="请输入折扣比例下限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="折扣比例上限" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['disc2']" placeholder="请输入折扣比例上限" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类在disc1—disc2 折扣范围内的单品的日均销量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dmsDisc']" placeholder="请输入小类在disc1—disc2 折扣范围内的单品的日均销量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类在原价销售时单品的日均销量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dmsOri']" placeholder="请输入小类在原价销售时单品的日均销量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="本条记录更新或新增日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择本条记录更新或新增日期" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>
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
    name: 'ZnXlDmsForm',
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
          add: "/intel/znXlDms/add",
          edit: "/intel/znXlDms/edit",
          queryById: "/intel/znXlDms/queryById"
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
          this.form.setFieldsValue(pick(this.model,'shopid','xlid','disc1','disc2','dmsDisc','dmsOri','sdate'))
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
        this.form.setFieldsValue(pick(row,'shopid','xlid','disc1','disc2','dmsDisc','dmsOri','sdate'))
      },
    }
  }
</script>