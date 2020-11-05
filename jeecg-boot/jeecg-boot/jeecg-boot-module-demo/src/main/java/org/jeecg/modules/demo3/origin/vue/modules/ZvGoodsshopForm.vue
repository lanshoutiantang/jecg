<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="shopid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopid']" placeholder="请输入shopid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="goodsid" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" placeholder="请输入goodsid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="flag" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['flag']" placeholder="请输入flag" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="status" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['status']" placeholder="请输入status"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="stocktype" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['stocktype']" placeholder="请输入stocktype" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="stocktypename" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['stocktypename']" placeholder="请输入stocktypename"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="logistics" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['logistics']" placeholder="请输入logistics"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="normalprice" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['normalprice']" placeholder="请输入normalprice" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="goodsdate" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsdate']" placeholder="请输入goodsdate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="deleted" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['deleted']" placeholder="请输入deleted"></a-input>
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
    name: 'ZvGoodsshopForm',
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
          add: "/origin/zvGoodsshop/add",
          edit: "/origin/zvGoodsshop/edit",
          queryById: "/origin/zvGoodsshop/queryById"
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
          this.form.setFieldsValue(pick(this.model,'shopid','goodsid','flag','status','stocktype','stocktypename','logistics','normalprice','goodsdate','deleted'))
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
        this.form.setFieldsValue(pick(row,'shopid','goodsid','flag','status','stocktype','stocktypename','logistics','normalprice','goodsdate','deleted'))
      },
    }
  }
</script>