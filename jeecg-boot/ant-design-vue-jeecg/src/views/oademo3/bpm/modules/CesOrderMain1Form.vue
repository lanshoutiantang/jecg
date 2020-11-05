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
              <j-date placeholder="请选择下单时间" v-decorator="['xdDate']" :trigger-change="true" style="width: 100%"/>
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
      <a-tab-pane tab="订单商品" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="cesOrderGoods1Table.loading"
          :columns="cesOrderGoods1Table.columns"
          :dataSource="cesOrderGoods1Table.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="订单客户" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="cesOrderCustomer1Table.loading"
          :columns="cesOrderCustomer1Table.columns"
          :dataSource="cesOrderCustomer1Table.dataSource"
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
  import {colAuthFilterJEditableTable} from "../../../../utils/authFilter";

  export default {
    name: 'CesOrderMain1Form',
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
        refKeys: ['cesOrderGoods1', 'cesOrderCustomer1', ],
        tableKeys:['cesOrderGoods1', 'cesOrderCustomer1', ],
        activeKey: 'cesOrderGoods1',
        // 订单商品
        cesOrderGoods1Table: {
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
        // 订单客户
        cesOrderCustomer1Table: {
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
          add: "/bpm/cesOrderMain1/add",
          edit: "/bpm/cesOrderMain1/edit",
          queryById: "/bpm/cesOrderMain1/queryById",
          cesOrderGoods1: {
            list: '/bpm/cesOrderMain1/queryCesOrderGoods1ByMainId'
          },
          cesOrderCustomer1: {
            list: '/bpm/cesOrderMain1/queryCesOrderCustomer1ByMainId'
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
        this.cesOrderGoods1Table.columns = colAuthFilterJEditableTable(this.cesOrderGoods1Table.columns,'yiduiduo:');
    },
    methods: {
      addBefore(){
        this.form.resetFields()
        this.cesOrderGoods1Table.dataSource=[]
        this.cesOrderCustomer1Table.dataSource=[]
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
          this.requestSubTableData(this.url.cesOrderGoods1.list, params, this.cesOrderGoods1Table)
          this.requestSubTableData(this.url.cesOrderCustomer1.list, params, this.cesOrderCustomer1Table)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          cesOrderGoods1List: allValues.tablesValue[0].values,
          cesOrderCustomer1List: allValues.tablesValue[1].values,
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