<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="cgid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['cgid']" placeholder="请输入cgid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="cgname" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['cgname']" placeholder="请输入cgname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="cg" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['cg']" placeholder="请输入cg"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="dlid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['dlid']" placeholder="请输入dlid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="dlname" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['dlname']" placeholder="请输入dlname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="dl" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['dl']" placeholder="请输入dl"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="zlid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['zlid']" placeholder="请输入zlid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="zlname" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['zlname']" placeholder="请输入zlname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="zl" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['zl']" placeholder="请输入zl"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="xlid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlid']" placeholder="请输入xlid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="xlname" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlname']" placeholder="请输入xlname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="xl" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xl']" placeholder="请输入xl"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="pdtjflag" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['pdtjflag', validatorRules.pdtjflag]" placeholder="请输入pdtjflag" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="qdhtj" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['qdhtj', validatorRules.qdhtj]" placeholder="请输入qdhtj" style="width: 100%"/>
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
    name: 'ZvCgDlZlXlForm',
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
          pdtjflag: {
            rules: [
              { required: true, message: '请输入pdtjflag!'},
            ]
          },
          qdhtj: {
            rules: [
              { required: true, message: '请输入qdhtj!'},
            ]
          },
        },
        url: {
          add: "/origin/zvCgDlZlXl/add",
          edit: "/origin/zvCgDlZlXl/edit",
          queryById: "/origin/zvCgDlZlXl/queryById"
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
          this.form.setFieldsValue(pick(this.model,'cgid','cgname','cg','dlid','dlname','dl','zlid','zlname','zl','xlid','xlname','xl','pdtjflag','qdhtj'))
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
        this.form.setFieldsValue(pick(row,'cgid','cgname','cg','dlid','dlname','dl','zlid','zlname','zl','xlid','xlname','xl','pdtjflag','qdhtj'))
      },
    }
  }
</script>