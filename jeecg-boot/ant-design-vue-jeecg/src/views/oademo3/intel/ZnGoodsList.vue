<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="日期">
              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.sdate_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.sdate_end"></j-date>
            </a-form-item>
          </a-col>
          <a-col :xl="5" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:shopname'" label="门店名称">
<!--              <a-input placeholder="请输入门店编码" v-model="queryParam.shopid"></a-input>-->
              <j-search-select-tag  placeholder="请选择门店名称"  v-model="queryParam.shopid" dict="v_zn_md_yt_ywq,shopname,shopid"/>
            </a-form-item>
          </a-col>
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:blname'" label="部类名称">
              <j-search-select-tag  placeholder="请选择部类名称" v-model="queryParam.blid" dict="blcode_name,blname,blid"/>
<!--              <j-popup placeholder="请选择部类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
            </a-form-item>
          </a-col>
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:klname'" label="课类名称">
              <j-search-select-tag  placeholder="请选择课类名称" v-model="queryParam.klid" dict="klcode_name,klname,klid"/>
<!--              <j-popup placeholder="请选择课类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
            </a-form-item>
          </a-col>
<!--          <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--            <a-form-item v-has="'bh:meiyong'" label="课类名称">-->
<!--              <j-search-select-tag v-has="'bh:meiyong'" placeholder="请选择课类名称" v-model="queryParam.klid" dict="klcode_name,klname,klid"/>-->
<!--              &lt;!&ndash;              <j-popup placeholder="请选择课类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>&ndash;&gt;-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类名称">
<!--              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
              <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>
            </a-form-item>
          </a-col>
<!--          <template v-if="toggleSearchStatus">-->
            <a-col :xl="4" :lg="7" :md="8" :sm="24">
              <a-form-item label="商品名称">
                <j-popup placeholder="请选择商品名称" v-model="queryParam.goodsname" code="zn_goodsid" org-fields="goodsname" dest-fields="goodsname" :field="getPopupField('goodsname')"/>
              </a-form-item>
            </a-col>
<!--          </template>-->
          <a-col :xl="3" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
<!--              <a @click="handleToggleSearch" style="margin-left: 8px">-->
<!--                {{ toggleSearchStatus ? '收起' : '展开' }}-->
<!--                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>-->
<!--              </a>-->
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" v-has="'bh:add'" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" v-has="'bh:download'" @click="handleExportXls('门店补货商品表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'bh:import'" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'bh:down'"  style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
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
          <a v-has="'bh:edit'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'bh:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <zn-goods-modal ref="modalForm" @ok="modalFormOk"></zn-goods-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnGoodsModal from './modules/ZnGoodsModal'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import {colAuthFilter} from "../../../utils/authFilter";
  import JDate from '@/components/jeecg/JDate.vue'


  export default {
    name: 'ZnGoodsList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JDate,
        JSearchSelectTag,
      ZnGoodsModal
    },
    data () {
      return {
        description: '门店补货商品表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
              sort:'',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'门店名称',
              sorter: true,
            align:"center",
            dataIndex: 'shopid_dictText'
          },
            {
                title:'部类名称',
                sorter: true,
                align:"center",
                dataIndex: 'blid_dictText'
            },
            {
                title:'课类名称',
                sorter: true,
                align:"center",
                dataIndex: 'klid_dictText'
            },
          // {
          //   title:'小类编码',
          //   align:"center",
          //   dataIndex: 'xlid'
          // },
            {
                title:'小类名称',
                sorter: true,
                align:"center",
                dataIndex: 'xlname'
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
            title:'补货类型',
              sorter: true,
            align:"center",
            dataIndex: 'classtype'
          },
          // {
          //   title:'季节属性',
          //   align:"center",
          //   dataIndex: 'seasontype'
          // },
          // {
          //   title:'季节性商品起季时首次到货日期',
          //   align:"center",
          //   dataIndex: 'arrdate'
          // },
          // {
          //   title:'季节下降点',
          //   align:"center",
          //   dataIndex: 'aweek'
          // },
          // {
          //   title:'季节下降点之后日均销量的折扣比例',
          //   align:"center",
          //   dataIndex: 'rate'
          // },
          // {
          //   title:'节日属性',
          //   align:"center",
          //   dataIndex: 'holidaytype'
          // },
          // {
          //   title:'节日开始日期',
          //   align:"center",
          //   dataIndex: 'holidayBegindate',
          //   customRender:function (text) {
          //     return !text?"":(text.length>10?text.substr(0,10):text)
          //   }
          // },
          // {
          //   title:'节日结束日期',
          //   align:"center",
          //   dataIndex: 'holidayEnddate',
          //   customRender:function (text) {
          //     return !text?"":(text.length>10?text.substr(0,10):text)
          //   }
          // },
          // {
          //   title:'春节指数',
          //   align:"center",
          //   dataIndex: 'zs'
          // },
          // {
          //   title:'节日安全系数',
          //   align:"center",
          //   dataIndex: 'hoRate'
          // },
          // {
          //   title:'定价',
          //   align:"center",
          //   dataIndex: 'normalprice'
          // },
          // {
          //   title:'最小库存数量',
          //   align:"center",
          //   dataIndex: 'minstock'
          // },
          // {
          //   title:'最小起订量',
          //   align:"center",
          //   dataIndex: 'minorder'
          // },
          {
            title:'昨日日终库存数量',
              sorter: true,
            align:"center",
            dataIndex: 'closeqty'
          },
          {
            title:'计算生成的补货数量',
              sorter: true,
            align:"center",
            dataIndex: 'qty'
          },
          {
            title:'补货标识',
              sorter: true,
            align:"center",
            dataIndex: 'flag_dictText'
          },
            // {
            //     title:'业务区机构编码',
            //     align:"center",
            //     dataIndex: 'ywqOrgan'
            // },
            // {
            //     title:'门店机构编码',
            //     align:"center",
            //     dataIndex: 'shopOrgan'
            // },
            // {
            //     title:'部类机构编码',
            //     align:"center",
            //     dataIndex: 'blOrgan'
            // },
            // {
            //     title:'科类机构编码',
            //     align:"center",
            //     dataIndex: 'klOrgan'
            // },
            // {
            //     title:'部类全部机构编码',
            //     align:"center",
            //     dataIndex: 'blOrganAll'
            // },
            // {
            //     title:'课类全部机构编码',
            //     align:"center",
            //     dataIndex: 'klOrganAll'
            // },
          {
            title:'更新日期时间',
            align:"center",
              sorter: true,
            dataIndex: 'sdate',
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
          list: "/intel/znGoods/list",
          delete: "/intel/znGoods/delete",
          deleteBatch: "/intel/znGoods/deleteBatch",
          exportXlsUrl: "/intel/znGoods/exportXls",
          importExcelUrl: "intel/znGoods/importExcel",
          
        },
        dictOptions:{},
      }
    },
    created() {
        this.disableMixinCreated=true;
        this.columns = colAuthFilter(this.columns,'bh:');
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