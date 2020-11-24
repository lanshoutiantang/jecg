<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-item label="促销计划编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planid']" placeholder="请输入促销计划编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24" >
            <a-form-item label="促销计划名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planname']" placeholder="请输入促销计划名称"></a-input>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="促销商品表" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="bZnPromGoodsTable.loading"
          :columns="bZnPromGoodsTable.columns"
          :dataSource="bZnPromGoodsTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="促销门店表" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="bZnPromShopTable.loading"
          :columns="bZnPromShopTable.columns"
          :dataSource="bZnPromShopTable.dataSource"
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

  export default {
    name: 'BZnPromPlanForm',
    mixins: [JEditableTableMixin],
    components: {
      JFormContainer,
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
        refKeys: ['bZnPromGoods', 'bZnPromShop', ],
        tableKeys:['bZnPromGoods', 'bZnPromShop', ],
        activeKey: 'bZnPromGoods',
        // 促销商品表
        bZnPromGoodsTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '序号',
              key: 'serialid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '商品编码',
              key: 'goodsid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        // 促销门店表
        bZnPromShopTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '序号',
              key: 'serialid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '门店编码',
              key: 'shopid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        url: {
          add: "/yyy/bZnPromPlan/add",
          edit: "/yyy/bZnPromPlan/edit",
          queryById: "/yyy/bZnPromPlan/queryById",
          bZnPromGoods: {
            list: '/yyy/bZnPromPlan/queryBZnPromGoodsByMainId'
          },
          bZnPromShop: {
            list: '/yyy/bZnPromPlan/queryBZnPromShopByMainId'
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
      addBefore(){
        this.form.resetFields()
        this.bZnPromGoodsTable.dataSource=[]
        this.bZnPromShopTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'planid','planname')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.bZnPromGoods.list, params, this.bZnPromGoodsTable)
          this.requestSubTableData(this.url.bZnPromShop.list, params, this.bZnPromShopTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          bZnPromGoodsList: allValues.tablesValue[0].values,
          bZnPromShopList: allValues.tablesValue[1].values,
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
       this.form.setFieldsValue(pick(row,'planid','planname'))
     },

    }
  }
</script>

<style scoped>
</style>