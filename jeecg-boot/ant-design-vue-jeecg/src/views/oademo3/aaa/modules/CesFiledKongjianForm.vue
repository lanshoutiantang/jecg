<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="输入框" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入输入框"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="下拉框" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['sex']" :trigger-change="true" dictCode="sex" placeholder="请选择下拉框"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="单选" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag :disabled="isDisabledAuth('kongjian:bukebianji')" type="radio" v-decorator="['radio']" :trigger-change="true" dictCode="sex" placeholder="请选择单选"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="复选框" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-multi-select-tag :disabled="isDisabledAuth('kongjian:bukebianji')"type="checkbox" v-decorator="['checkbox']" :trigger-change="true" dictCode="sex" placeholder="请选择复选框"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="下拉多选" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-multi-select-tag :disabled="isDisabledAuth('kongjian:bukebianji')" type="list_multi" v-decorator="['selMul']" :trigger-change="true" dictCode="sex" placeholder="请选择下拉多选"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="下拉搜寻" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-search-select-tag v-decorator="['selSearch']" dict="sys_user,realname,username" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-image-upload isMultiple v-decorator="['pic']"></j-image-upload>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="文件" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-upload v-decorator="['files']" :trigger-change="true"></j-upload>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="markdown" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['remark']" placeholder="请输入markdown"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="富文本" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-editor v-decorator="['fuwenben',{trigger:'input'}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择日期" v-decorator="['birthday']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="用户" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-select-user-by-dep v-decorator="['userSel']"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-select-depart v-decorator="['depSel']" multi/>
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
  import JUpload from '@/components/jeecg/JUpload'
  import JImageUpload from '@/components/jeecg/JImageUpload'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JSelectUserByDep from '@/components/jeecgbiz/JSelectUserByDep'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import JEditor from '@/components/jeecg/JEditor'
  import {disabledAuthFilter} from "../../../../utils/authFilter";

  export default {
    name: 'CesFiledKongjianForm',
    components: {
      JFormContainer,
      JDate,
      JUpload,
      JImageUpload,
      JSelectDepart,
      JSelectUserByDep,
      JDictSelectTag,
      JMultiSelectTag,
      JSearchSelectTag,
      JEditor,
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
          add: "/aaa/cesFiledKongjian/add",
          edit: "/aaa/cesFiledKongjian/edit",
          queryById: "/aaa/cesFiledKongjian/queryById"
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
            return disabledAuthFilter(code);
        },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'createTime','name','sex','radio','checkbox','selMul','selSearch','pic','files','remark','fuwenben','birthday','userSel','depSel'))
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
        this.form.setFieldsValue(pick(row,'createTime','name','sex','radio','checkbox','selMul','selSearch','pic','files','remark','fuwenben','birthday','userSel','depSel'))
      },
    }
  }
</script>