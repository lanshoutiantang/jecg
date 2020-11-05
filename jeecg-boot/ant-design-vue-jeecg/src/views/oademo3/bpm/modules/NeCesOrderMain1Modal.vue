<template>
  <j-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :xs="24" :sm="12">
            <a-form-item label="订单编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderCode']" placeholder="请输入订单编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="下单时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择下单时间" v-decorator="['xdDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="订单金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['money']" placeholder="请输入订单金额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['remark']" placeholder="请输入备注"></a-input>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="Ne订单商品" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="neCesOrderGoods1Table.loading"
            :columns="neCesOrderGoods1Table.columns"
            :dataSource="neCesOrderGoods1Table.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
        <a-tab-pane tab="Ne订单客户" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="neCesOrderCustomer1Table.loading"
            :columns="neCesOrderCustomer1Table.columns"
            :dataSource="neCesOrderCustomer1Table.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
      </a-tabs>

    </a-spin>
  </j-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'NeCesOrderMain1Modal',
    mixins: [JEditableTableMixin],
    components: {
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
        refKeys: ['neCesOrderGoods1', 'neCesOrderCustomer1', ],
        tableKeys:['neCesOrderGoods1', 'neCesOrderCustomer1', ],
        activeKey: 'neCesOrderGoods1',
        // Ne订单商品
        neCesOrderGoods1Table: {
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
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '数量',
              key: 'num',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '单品总价',
              key: 'zongPrice',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        // Ne订单客户
        neCesOrderCustomer1Table: {
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
              type: FormTypes.input,
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
          add: "/bpm/neCesOrderMain1/add",
          edit: "/bpm/neCesOrderMain1/edit",
          neCesOrderGoods1: {
            list: '/bpm/neCesOrderMain1/queryNeCesOrderGoods1ByMainId'
          },
          neCesOrderCustomer1: {
            list: '/bpm/neCesOrderMain1/queryNeCesOrderCustomer1ByMainId'
          },
        }
      }
    },
    methods: {
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
          this.requestSubTableData(this.url.neCesOrderGoods1.list, params, this.neCesOrderGoods1Table)
          this.requestSubTableData(this.url.neCesOrderCustomer1.list, params, this.neCesOrderCustomer1Table)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          neCesOrderGoods1List: allValues.tablesValue[0].values,
          neCesOrderCustomer1List: allValues.tablesValue[1].values,
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