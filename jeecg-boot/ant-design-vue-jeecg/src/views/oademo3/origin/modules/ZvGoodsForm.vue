<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品分类" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['barcodeid']" placeholder="请输入商品分类"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品分类（小类）" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['deptid']" placeholder="请输入商品分类（小类）"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入商品名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['flag', validatorRules.flag]" placeholder="请输入商品状态" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品状态名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['status', validatorRules.status]" placeholder="请输入商品状态名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="品牌" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['brand']" placeholder="请输入品牌"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="规格" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['spec']" placeholder="请输入规格"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="建档日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择建档日期" v-decorator="['indate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="删除状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['deleted']" placeholder="请输入删除状态"></a-input>
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
    name: 'ZvGoodsForm',
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
          flag: {
            rules: [
              { required: true, message: '请输入商品状态!'},
            ]
          },
          status: {
            rules: [
              { required: true, message: '请输入商品状态名称!'},
            ]
          },
        },
        url: {
          add: "/origin/zvGoods/add",
          edit: "/origin/zvGoods/edit",
          queryById: "/origin/zvGoods/queryById"
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
          this.form.setFieldsValue(pick(this.model,'goodsid','barcodeid','deptid','name','flag','status','brand','spec','indate','deleted'))
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
        this.form.setFieldsValue(pick(row,'goodsid','barcodeid','deptid','name','flag','status','brand','spec','indate','deleted'))
      },
    }
  }
</script>