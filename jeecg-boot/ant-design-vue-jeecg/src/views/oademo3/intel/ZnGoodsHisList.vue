<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="门店编码">
              <a-input placeholder="请输入门店编码" v-model="queryParam.shopid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类编码">
              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="商品编码">
                <a-input placeholder="请输入商品编码" v-model="queryParam.goodsid"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="商品类别">
                <a-input placeholder="请输入商品类别" v-model="queryParam.classtype"></a-input>
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
      <a-button @click="handleAdd" v-has="'others:add'" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" v-has="'others:download'" @click="handleExportXls('门店补货商品历史表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'others:import'" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'others:down'" style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
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
          <a v-has="'others:edit'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'others:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <zn-goods-his-modal ref="modalForm" @ok="modalFormOk"></zn-goods-his-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnGoodsHisModal from './modules/ZnGoodsHisModal'

  export default {
    name: 'ZnGoodsHisList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ZnGoodsHisModal
    },
    data () {
      return {
        description: '门店补货商品历史表管理页面',
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
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建日期',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'更新人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title:'更新日期',
            align:"center",
            dataIndex: 'updateTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'所属部门',
            align:"center",
            dataIndex: 'sysOrgCode'
          },
          {
            title:'门店编码',
            align:"center",
            dataIndex: 'shopid'
          },
          {
            title:'小类编码',
            align:"center",
            dataIndex: 'xlid'
          },
          {
            title:'商品编码',
            align:"center",
            dataIndex: 'goodsid'
          },
          {
            title:'商品类别',
            align:"center",
            dataIndex: 'classtype'
          },
          {
            title:'定价',
            align:"center",
            dataIndex: 'normalprice'
          },
          {
            title:'最小库存数量',
            align:"center",
            dataIndex: 'minstock'
          },
          {
            title:'最小起订量',
            align:"center",
            dataIndex: 'minorder'
          },
          {
            title:'昨日日终库存数量',
            align:"center",
            dataIndex: 'closeqty'
          },
          {
            title:'计算生成的补货数量',
            align:"center",
            dataIndex: 'qty'
          },
          {
            title:'补货标识',
            align:"center",
            dataIndex: 'flag'
          },
          {
            title:'更新日期时间',
            align:"center",
            dataIndex: 'sdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'季节开始日期',
            align:"center",
            dataIndex: 'seasonBegindate'
          },
          {
            title:'节日开始日期',
            align:"center",
            dataIndex: 'holidayBegindate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'季节属性',
            align:"center",
            dataIndex: 'seasontype'
          },
          {
            title:'节日属性',
            align:"center",
            dataIndex: 'holidaytype'
          },
          {
            title:'节日结束日期',
            align:"center",
            dataIndex: 'holidayEnddate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
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
          list: "/intel/znGoodsHis/list",
          delete: "/intel/znGoodsHis/delete",
          deleteBatch: "/intel/znGoodsHis/deleteBatch",
          exportXlsUrl: "/intel/znGoodsHis/exportXls",
          importExcelUrl: "intel/znGoodsHis/importExcel",
          
        },
        dictOptions:{},
      }
    },
    created() {
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