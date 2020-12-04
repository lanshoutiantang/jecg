<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="促销计划名称">
              <a-input placeholder="请输入促销计划名称" v-model="queryParam.planname"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="促销开始日期时间">
              <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择促销开始日期时间" v-model="queryParam.begindate"></j-date>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="促销结束日期时间">
                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择促销结束日期时间" v-model="queryParam.enddate"></j-date>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="门店名称">
<!--                <a-input placeholder="请输入门店编码" v-model="queryParam.shopid"></a-input>-->
                <j-search-select-tag placeholder="请选择门店名称" v-model="queryParam.shopid" dict="tb_organ,name,organ"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="商品名称">
<!--                <a-input placeholder="请输入商品编码" v-model="queryParam.goodsid"></a-input>-->
                <j-popup placeholder="请选择商品名称" v-model="queryParam.goodsname" code="zn_goodsid" org-fields="goodsname" dest-fields="goodsname" :field="getPopupField('goodsname')"/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary"  v-has="'cuxiao:add1'" icon="plus">新增</a-button>
      <a-button type="primary" icon="download"  v-has="'cuxiao:download1'"  @click="handleExportXls('促销明细表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'cuxiao:import1'" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button  v-has="'cuxiao:down1'" style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a v-has="'cuxiao:edit1'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'cuxiao:delete1'" >删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <zn-prom-detail-modal ref="modalForm" @ok="modalFormOk"></zn-prom-detail-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnPromDetailModal from './modules/ZnPromDetailModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import {colAuthFilter} from "../../../utils/authFilter";
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'

  export default {
    name: 'ZnPromDetailList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JSearchSelectTag,
      JDate,
      ZnPromDetailModal
    },
    data () {
      return {
        description: '促销明细表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
            // {
            //     title:'操作人',
            //     align:"center",
            //     dataIndex: 'createBy'
            // },
          {
            title:'促销计划编号',
              sorter: true,
            align:"center",
            dataIndex: 'planid'
          },
          {
            title:'促销计划名称',
              sorter: true,
            align:"center",
            dataIndex: 'planname'
          },
          {
            title:'促销开始日期时间',
              sorter: true,
            align:"center",
            dataIndex: 'begindate'
          },
          {
            title:'促销结束日期时间',
              sorter: true,
            align:"center",
            dataIndex: 'enddate'
          },
          {
            title:'门店名称',
              sorter: true,
            align:"center",
            dataIndex: 'shopid_dictText'
          },
          // {
          //   title:'商品编码',
          //   align:"center",
          //   dataIndex: 'goodsid'
          // },
            {
                title:'商品名称',
                sorter: true,
                align:"center",
                dataIndex: 'goodsname'
            },
          {
            title:'促销售价',
              sorter: true,
            align:"center",
            dataIndex: 'price'
          },
          {
            title:'采购分货数量',
              sorter: true,
            align:"center",
            dataIndex: 'planqty'
          },
          {
            title:'补货标识',
              sorter: true,
            align:"center",
            dataIndex: 'flag'
          },
            // {
            //     title:'门店机构编码',
            //     align:"center",
            //     dataIndex: 'shopOrgan'
            // },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/intel/znPromDetail/list",
          delete: "/intel/znPromDetail/delete",
          deleteBatch: "/intel/znPromDetail/deleteBatch",
          exportXlsUrl: "/intel/znPromDetail/exportXls",
          importExcelUrl: "intel/znPromDetail/importExcel",
          
        },
        dictOptions:{},
      }
    },
    created() {
        this.disableMixinCreated=true;
        this.columns = colAuthFilter(this.columns,'cuxiao:');
        this.loadData();
        this.initDictConfig();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>