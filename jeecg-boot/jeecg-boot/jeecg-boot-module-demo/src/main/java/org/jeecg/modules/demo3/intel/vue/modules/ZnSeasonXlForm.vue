<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="业务区标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['ywqid']" :trigger-change="true" dictCode="city" placeholder="请选择业务区标识"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['xlid']"
                :trigger-change="true"
                org-fields="xlname"
                dest-fields="xlid"
                code="zn_xlid"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节开始日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['begindate']" placeholder="请输入季节开始日期"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['enddate']" placeholder="请输入季节结束日期"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节性商品起季是首次到货日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['arrdate']" placeholder="请输入季节性商品起季是首次到货日期"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节时间达到Period1，日均销量的折扣比例" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['rate1']" placeholder="请输入季节时间达到Period1，日均销量的折扣比例" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节时间达到Period2，日均销量的折扣比例" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['rate2']" placeholder="请输入季节时间达到Period2，日均销量的折扣比例" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节时间达到的比例2" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['period2']" placeholder="请输入季节时间达到的比例2" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节时间达到的比例1" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['period1']" placeholder="请输入季节时间达到的比例1" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="数据更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择数据更新日期" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>
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
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: 'ZnSeasonXlForm',
    components: {
      JFormContainer,
      JDate,
      JDictSelectTag,
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
          add: "/intel/znSeasonXl/add",
          edit: "/intel/znSeasonXl/edit",
          queryById: "/intel/znSeasonXl/queryById"
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
          this.form.setFieldsValue(pick(this.model,'ywqid','xlid','begindate','enddate','arrdate','rate1','rate2','period2','period1','sdate'))
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
        this.form.setFieldsValue(pick(row,'ywqid','xlid','begindate','enddate','arrdate','rate1','rate2','period2','period1','sdate'))
      },
    }
  }
</script>