<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-item label="订单编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderCode']" placeholder="请输入订单编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24" >
            <a-form-item label="下单时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date :getCalendarContainer="getParentModal" placeholder="请选择下单时间" v-decorator="['xdDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24" >
            <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['money']" placeholder="请输入订单金额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24" >
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['remark']" placeholder="请输入备注"></a-input>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="订单1商品" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="cesOrder1Goods1Table.loading"
          :columns="cesOrder1Goods1Table.columns"
          :dataSource="cesOrder1Goods1Table.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="订单1客户" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="cesOrder1Customer1Table.loading"
          :columns="cesOrder1Customer1Table.columns"
          :dataSource="cesOrder1Customer1Table.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'CesOrder1Main1Form',
    mixins: [JEditableTableMixin],
    components: {
      JFormContainer,
      JDate,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['cesOrder1Goods1', 'cesOrder1Customer1', ],
        tableKeys:['cesOrder1Goods1', 'cesOrder1Customer1', ],
        activeKey: 'cesOrder1Goods1',
        // 订单1商品
        cesOrder1Goods1Table: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '商品名称',
              key: 'goodsName',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '价格',
              key: 'price',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '数量',
              key: 'num',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '单品总价',
              key: 'zongPrice',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        // 订单1客户
        cesOrder1Customer1Table: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '客户名字',
              key: 'name',
              type: FormTypes.popup,
              popupCode:"tj_user_report1",
              destFields:"name,sex,birthday",
              orgFields:"realname,sex,birthday",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '性别',
              key: 'sex',
              type: FormTypes.select,
              dictCode:"sex",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '生日',
              key: 'birthday',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '年龄',
              key: 'age',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '地址',
              key: 'address',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },



          ]
        },
        url: {
          add: "/chongzuo/cesOrder1Main1/add",
          edit: "/chongzuo/cesOrder1Main1/edit",
          queryById: "/chongzuo/cesOrder1Main1/queryById",
          cesOrder1Goods1: {
            list: '/chongzuo/cesOrder1Main1/queryCesOrder1Goods1ByMainId'
          },
          cesOrder1Customer1: {
            list: '/chongzuo/cesOrder1Main1/queryCesOrder1Customer1ByMainId'
          },
        }
      }
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：false流程表单 true普通表单
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
        getParentModal(){
            return document.querySelector('.jee-outer-modal')
        },
      addBefore(){
        this.form.resetFields()
        this.cesOrder1Goods1Table.dataSource=[]
        this.cesOrder1Customer1Table.dataSource=[]
      },
        getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'orderCode','xdDate','money','remark')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.cesOrder1Goods1.list, params, this.cesOrder1Goods1Table)
          this.requestSubTableData(this.url.cesOrder1Customer1.list, params, this.cesOrder1Customer1Table)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          cesOrder1Goods1List: allValues.tablesValue[0].values,
          cesOrder1Customer1List: allValues.tablesValue[1].values,
        }
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          })
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'orderCode','xdDate','money','remark'))
     },

    }
  }
</script>

<style scoped>
</style>