<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="业务区名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <a-input v-decorator="['ywqid']" placeholder="请输入业务区标识"></a-input>-->
              <j-dict-select-tag type="list" v-decorator="['ywqid']" :trigger-change="true" dictCode="city" placeholder="请选择业务区名称"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidayid']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入节日代码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['holidayname']"
                :trigger-change="true"
                org-fields="holidayname,holidayid"
                dest-fields="holidayname,holidayid"
                code="zn_holidayname"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
<!--          <a-col :span="24">-->
<!--            <a-form-item label="节日代码" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--&lt;!&ndash;              <a-input v-decorator="['holidayid']" placeholder="请输入节日代码"></a-input>&ndash;&gt;-->
<!--              <j-popup-->
<!--                v-decorator="['holidayid']"-->
<!--                :trigger-change="false"-->
<!--                org-fields="holidayname"-->
<!--                dest-fields="holidayid"-->
<!--                code="zn_holidayname"-->
<!--                @callback="popupCallback"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="24">
            <a-form-item label="小类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlid']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入小类编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['xlname']"
                :trigger-change="true"
                org-fields="xlname,xlid"
                dest-fields="xlname,xlid"
                code="zn_xlid"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="去年小类销售额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['salevalue']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入去年小类销售额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="春节指数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['zs']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入春节指数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日安全系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['rate']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入节日安全系数" style="width: 100%"/>
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
  import {disabledAuthFilter} from "../../../../utils/authFilter";

  export default {
    name: 'ZnHolidayXlForm',
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
          add: "/intel/znHolidayXl/add",
          edit: "/intel/znHolidayXl/edit",
          queryById: "/intel/znHolidayXl/queryById"
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
        isDisabledAuth(code){
            return disabledAuthFilter(code);},
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'ywqid','holidayid','holidayname','xlid','xlname','salevalue','zs','rate'))
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
        this.form.setFieldsValue(pick(row,'ywqid','holidayid','holidayname','xlid','xlname','salevalue','zs','rate'))
      },
    }
  }
</script>