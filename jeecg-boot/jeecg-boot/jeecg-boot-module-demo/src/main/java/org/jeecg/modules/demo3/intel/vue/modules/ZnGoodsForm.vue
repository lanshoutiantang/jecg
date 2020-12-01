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
            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['classtype']" placeholder="请输入补货类型"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节属性" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['seasontype']" placeholder="请输入季节属性"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节性商品起季时首次到货日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['arrdate']" placeholder="请输入季节性商品起季时首次到货日期"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节下降点" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['aweek']" placeholder="请输入季节下降点" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="季节下降点之后日均销量的折扣比例" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['rate']" placeholder="请输入季节下降点之后日均销量的折扣比例"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日属性" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidaytype']" placeholder="请输入节日属性"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日开始日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择节日开始日期" v-decorator="['holidayBegindate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择节日结束日期" v-decorator="['holidayEnddate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="春节指数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['zs']" placeholder="请输入春节指数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日安全系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['hoRate']" placeholder="请输入节日安全系数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="定价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['normalprice']" placeholder="请输入定价" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="最小库存数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['minstock']" placeholder="请输入最小库存数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="最小起订量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['minorder']" placeholder="请输入最小起订量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="昨日日终库存数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['closeqty']" placeholder="请输入昨日日终库存数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="计算生成的补货数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['qty']" placeholder="请输入计算生成的补货数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['flag']" placeholder="请输入补货标识"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="更新日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择更新日期时间" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>
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
    name: 'ZnGoodsForm',
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
          add: "/intel/znGoods/add",
          edit: "/intel/znGoods/edit",
          queryById: "/intel/znGoods/queryById"
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
          this.form.setFieldsValue(pick(this.model,'shopid','xlid','goodsid','classtype','seasontype','arrdate','aweek','rate','holidaytype','holidayBegindate','holidayEnddate','zs','hoRate','normalprice','minstock','minorder','closeqty','qty','flag','sdate'))
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
        this.form.setFieldsValue(pick(row,'shopid','xlid','goodsid','classtype','seasontype','arrdate','aweek','rate','holidaytype','holidayBegindate','holidayEnddate','zs','hoRate','normalprice','minstock','minorder','closeqty','qty','flag','sdate'))
      },
    }
  }
</script>