<style lang="less" scoped>
  @import './customer.less';
</style>

<template>
  <div>
    <Card>
      <Row>
        <Col :xs="16" :sm="14" :md="10" :lg="6">
          <Input placeholder="关键字搜索" search enter-button :disabled="customerKeySearchSubmitting" @on-search="keySearchCustomer($event)" />
        </Col>
        <Col :xs="8" :sm="10" :md="14" :lg="18">
          <Poptip class="pro-float-right" title="精准查询" placement="left-start" width="900" v-model="customerQueryPoptipShow">
            <Button icon="ios-locate-outline">精准查询</Button>
            <div slot="content">
              <Form class="pro-margin-top-10" ref="customerQueryForm" :model="customerQuery" :label-width="120">
                <Row>
                  <Col span="8">
                    <FormItem label="客户序号：" prop="customerNo">
                      <Input v-model="customerQuery.customerNo" placeholder="输入客户序号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="客户名称：" prop="customerName">
                      <Input v-model="customerQuery.customerName" placeholder="输入客户名称"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="社会统一信用代码：" prop="creditCode">
                      <Input v-model="customerQuery.creditCode" placeholder="输入社会统一信用代码"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="地址：" prop="address">
                      <Input v-model="customerQuery.address" placeholder="输入地址"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="传真：" prop="fax">
                      <Input v-model="customerQuery.fax" placeholder="输入传真"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="法定代表人：" prop="legalRepresentative">
                      <Input v-model="customerQuery.legalRepresentative" placeholder="输入法定代表人"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="法定代表人电话：" prop="legalRepresentativePhone">
                      <Input v-model="customerQuery.legalRepresentativePhone" placeholder="输入法定代表人电话"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="法定代表人传真：" prop="legalRepresentativeFax">
                      <Input v-model="customerQuery.legalRepresentativeFax" placeholder="输入法定代表人传真"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="委托代理人：" prop="agent">
                      <Input v-model="customerQuery.agent" placeholder="输入委托代理人"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="委托代理人电话：" prop="agentPhone">
                      <Input v-model="customerQuery.agentPhone" placeholder="输入委托代理人电话"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="委托代理人传真：" prop="agentFax">
                      <Input v-model="customerQuery.agentFax" placeholder="输入委托代理人传真"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="开户银行：" prop="openingBank">
                      <Input v-model="customerQuery.openingBank" placeholder="输入开户银行"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="银行账号：" prop="bankAccount">
                      <Input v-model="customerQuery.bankAccount" placeholder="输入银行账号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="纳税登记号：" prop="taxRegistrationNumber">
                      <Input v-model="customerQuery.taxRegistrationNumber" placeholder="输入纳税登记号"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="联系电话：" prop="phoneNumber">
                      <Input v-model="customerQuery.phoneNumber" placeholder="输入联系电话"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="账户余额：" prop="accountBalance">
                      <InputNumber v-model="customerQuery.accountBalance"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="可用余额：" prop="availableBalance">
                      <InputNumber v-model="customerQuery.availableBalance"></InputNumber>
                    </FormItem>
                  </Col>
                </Row>
                <Divider />
                <Row>
                  <Col span="24">
                    <FormItem class="pro-float-right">
                      <Button type="primary" @click="vagueQueryCustomer()" :loading="customerQuerySubmitting">精准查询</Button>
                      <Button class="pro-margin-left-8" @click="resetCustomerQueryForm()">重置</Button>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
          </Poptip>
        </Col>
      </Row>
      <Button class="pro-list-button" type="primary" icon="ios-add-circle-outline" ghost @click="addCustomer()">新建</Button>
      <Button class="pro-list-button" type="dashed" icon="ios-brush-outline" @click="upCustomer()" v-if="customerSelectRowLength === 1">编辑</Button>
      <Poptip class="pro-list-button" title="确认删除？" placement="bottom" confirm @on-ok="delCustomer()" v-if="customerSelectRowLength > 0">
        <Button type="error" icon="ios-backspace-outline" ghost>删除</Button>
      </Poptip>
      <Button class="pro-list-button" icon="md-bulb" @click="detailCustomer()" v-if="customerSelectRowLength === 1">详情</Button>
      <Poptip class="pro-margin-top-12 pro-float-right" placement="left-start" width="173">
        <Button type="text" size="large" icon="ios-keypad-outline"></Button>
        <div slot="content">
          <CheckboxGroup class="pro-margin-top-12" v-model="customerColumnsChecked" @on-change="changeCustomerColumns">
            <Row type="flex" justify="center">
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="customerNo">客户序号</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="customerName">客户名称</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="creditCode">社会统一信用代码</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="address">地址</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="fax">传真</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="legalRepresentative">法定代表人</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="legalRepresentativePhone">法定代表人电话</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="legalRepresentativeFax">法定代表人传真</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="agent">委托代理人</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="agentPhone">委托代理人电话</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="agentFax">委托代理人传真</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="openingBank">开户银行</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="bankAccount">银行账号</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="taxRegistrationNumber">纳税登记号</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="phoneNumber">联系电话</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="accountBalance">账户余额</Checkbox>
              </Col>
              <Col span="24">
                <Checkbox class="pro-margin-left-2" label="availableBalance">可用余额</Checkbox>
              </Col>
            </Row>
          </CheckboxGroup>
          <Divider />
          <Button class="pro-margin-left-2 pro-margin-bottom-12" @click="cleanCustomerColumns()">清空</Button>
          <Button class="pro-margin-left-8 pro-margin-bottom-12" @click="resetCustomerColumns()">重置</Button>
        </div>
      </Poptip>
      <Collapse simple class="pro-margin-bottom-10" value="0">
        <Panel>
          合计
          <div slot="content">
            <Table :columns="customerSumColumns" :data="customerSumSet"></Table>
          </div>
        </Panel>
      </Collapse>
      <Table :columns="customerColumns" :data="customerSet" @on-selection-change="setCustomerSelectRow($event)" @on-row-dblclick="dblclickDetailCustomer($event)"></Table>
      <Row class="pro-margin-top-10" type="flex" justify="center">
        <Page size="small" placement="top" show-total show-sizer show-elevator :total="customerCount" :current="customerPage" :page-size-opts="[10, 30, 50]" @on-change="customerPageIndexChange($event)" @on-page-size-change="customerPageSizeChange($event)" v-if="customerPageShow" />
        <Button type="text" shape="circle" icon="md-refresh" @click="customerRefresh()"></Button>
      </Row>
      <Spin size="large" fix v-if="customerSpinShow"></Spin>
    </Card>
    <Modal width="60" v-model="customerEditModalShow" :closable="false" :mask-closable="false" :footer-hide="true">
      <Card>
        <p slot="title">
          <Icon type="ios-brush-outline"></Icon>
          编辑客户
        </p>
        <Form class="pro-margin-top-20" ref="customerForm" :model="customer" :rules="customerFormValidate" :label-width="120">
          <Row type="flex" justify="center">
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="客户序号：" prop="customerNo">
                <Input v-model="customer.customerNo" placeholder="输入客户序号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="客户名称：" prop="customerName">
                <Input v-model="customer.customerName" placeholder="输入客户名称"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="社会统一信用代码：" prop="creditCode">
                <Input v-model="customer.creditCode" placeholder="输入社会统一信用代码"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="地址：" prop="address">
                <Input v-model="customer.address" placeholder="输入地址"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="传真：" prop="fax">
                <Input v-model="customer.fax" placeholder="输入传真"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="法定代表人：" prop="legalRepresentative">
                <Input v-model="customer.legalRepresentative" placeholder="输入法定代表人"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="法定代表人电话：" prop="legalRepresentativePhone">
                <Input v-model="customer.legalRepresentativePhone" placeholder="输入法定代表人电话"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="法定代表人传真：" prop="legalRepresentativeFax">
                <Input v-model="customer.legalRepresentativeFax" placeholder="输入法定代表人传真"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="委托代理人：" prop="agent">
                <Input v-model="customer.agent" placeholder="输入委托代理人"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="委托代理人电话：" prop="agentPhone">
                <Input v-model="customer.agentPhone" placeholder="输入委托代理人电话"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="委托代理人传真：" prop="agentFax">
                <Input v-model="customer.agentFax" placeholder="输入委托代理人传真"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="开户银行：" prop="openingBank">
                <Input v-model="customer.openingBank" placeholder="输入开户银行"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="银行账号：" prop="bankAccount">
                <Input v-model="customer.bankAccount" placeholder="输入银行账号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="纳税登记号：" prop="taxRegistrationNumber">
                <Input v-model="customer.taxRegistrationNumber" placeholder="输入纳税登记号"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="联系电话：" prop="phoneNumber">
                <Input v-model="customer.phoneNumber" placeholder="输入联系电话"></Input>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="账户余额：" prop="accountBalance">
                <InputNumber v-model="customer.accountBalance"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem label="可用余额：" prop="availableBalance">
                <InputNumber v-model="customer.availableBalance"></InputNumber>
              </FormItem>
            </Col>
            <Col :xs="24" :sm="20" :md="18" :lg="14">
              <FormItem>
                <Button class="pro-margin-right-8" @click="toCustomerList()">取消</Button>
                <Button class="pro-margin-right-8" @click="resetCustomerForm()">重置</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="customerSubmitting" @click="saveCustomerForm()">保存</Button>
                <Button class="pro-margin-right-8" type="primary" :loading="customerSubmitting" @click="saveAddCustomerForm()">继续录入</Button>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    </Modal>
    <Drawer width="45" v-model="customerDetailDrawerShow" :closable="false">
      <Card>
        <p slot="title">
          <Icon type="md-bulb"></Icon>
          客户详情
        </p>
        <Row>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>客户编号：{{ customer.customerId }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>客户序号：{{ customer.customerNo }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>客户名称：{{ customer.customerName }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>社会统一信用代码：{{ customer.creditCode }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>地址：{{ customer.address }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>传真：{{ customer.fax }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>法定代表人：{{ customer.legalRepresentative }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>法定代表人电话：{{ customer.legalRepresentativePhone }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>法定代表人传真：{{ customer.legalRepresentativeFax }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>委托代理人：{{ customer.agent }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>委托代理人电话：{{ customer.agentPhone }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>委托代理人传真：{{ customer.agentFax }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>开户银行：{{ customer.openingBank }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>银行账号：{{ customer.bankAccount }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>纳税登记号：{{ customer.taxRegistrationNumber }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>联系电话：{{ customer.phoneNumber }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>账户余额：{{ customer.accountBalance }}</p>
            <Divider dashed />
          </Col>
          <Col :xs="24" :sm="24" :md="24" :lg="24">
            <p>可用余额：{{ customer.availableBalance }}</p>
            <Divider dashed />
          </Col>
        </Row>
      </Card>
    </Drawer>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import Customer from '@/view/cn/moonnow/customer/management/customer/Customer'
import CustomerQuery from '@/view/cn/moonnow/customer/management/customer/CustomerQuery'
import { Decimal } from 'decimal.js'

export default {
  data () {
    return {
      customerColumns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '客户序号', key: 'customerNo', minWidth: 200, sortable: true },
        { title: '客户名称', key: 'customerName', minWidth: 200, sortable: true },
        { title: '社会统一信用代码', key: 'creditCode', minWidth: 200, sortable: true },
        { title: '地址', key: 'address', minWidth: 200, sortable: true },
        { title: '传真', key: 'fax', minWidth: 200, sortable: true },
        { title: '法定代表人', key: 'legalRepresentative', minWidth: 200, sortable: true },
        { title: '法定代表人电话', key: 'legalRepresentativePhone', minWidth: 200, sortable: true },
        { title: '法定代表人传真', key: 'legalRepresentativeFax', minWidth: 200, sortable: true },
        { title: '委托代理人', key: 'agent', minWidth: 200, sortable: true },
        { title: '委托代理人电话', key: 'agentPhone', minWidth: 200, sortable: true },
        { title: '委托代理人传真', key: 'agentFax', minWidth: 200, sortable: true },
        { title: '开户银行', key: 'openingBank', minWidth: 200, sortable: true },
        { title: '银行账号', key: 'bankAccount', minWidth: 200, sortable: true },
        { title: '纳税登记号', key: 'taxRegistrationNumber', minWidth: 200, sortable: true },
        { title: '联系电话', key: 'phoneNumber', minWidth: 200, sortable: true },
        { title: '账户余额', key: 'accountBalance', minWidth: 200, sortable: true },
        { title: '可用余额', key: 'availableBalance', minWidth: 200, sortable: true }
      ],
      customerFormValidate: {
        customerNo: [
          { required: true, message: '客户序号不能为空', trigger: 'blur' }
        ],
        customerName: [
          { required: true, message: '客户名称不能为空', trigger: 'blur' }
        ],
        creditCode: [
          { required: true, message: '社会统一信用代码不能为空', trigger: 'blur' }
        ]
      },
      customerSet: [],
      customerCount: 0,
      customerRows: 10,
      customerPage: 1,
      customerSelectRow: [],
      customerSelectRowLength: 0,
      customer: new Customer(),
      customerQuery: new CustomerQuery(),
      customerSpinShow: false,
      customerPageShow: true,
      customerEditModalShow: false,
      customerSubmitting: false,
      customerContinue: false,
      customerDetailDrawerShow: false,
      customerQueryPoptipShow: false,
      customerQuerySubmitting: false,
      customerKeySearchSubmitting: false,
      customerKeySearchClickRate: 0,
      customerColumnsChecked: [
        'customerId',
        'customerNo',
        'customerName',
        'creditCode',
        'address',
        'fax',
        'legalRepresentative',
        'legalRepresentativePhone',
        'legalRepresentativeFax',
        'agent',
        'agentPhone',
        'agentFax',
        'openingBank',
        'bankAccount',
        'taxRegistrationNumber',
        'phoneNumber',
        'accountBalance',
        'availableBalance'
      ],
      customerColumnsNexus: {
        customerId: { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        customerNo: { title: '客户序号', key: 'customerNo', minWidth: 200, sortable: true },
        customerName: { title: '客户名称', key: 'customerName', minWidth: 200, sortable: true },
        creditCode: { title: '社会统一信用代码', key: 'creditCode', minWidth: 200, sortable: true },
        address: { title: '地址', key: 'address', minWidth: 200, sortable: true },
        fax: { title: '传真', key: 'fax', minWidth: 200, sortable: true },
        legalRepresentative: { title: '法定代表人', key: 'legalRepresentative', minWidth: 200, sortable: true },
        legalRepresentativePhone: { title: '法定代表人电话', key: 'legalRepresentativePhone', minWidth: 200, sortable: true },
        legalRepresentativeFax: { title: '法定代表人传真', key: 'legalRepresentativeFax', minWidth: 200, sortable: true },
        agent: { title: '委托代理人', key: 'agent', minWidth: 200, sortable: true },
        agentPhone: { title: '委托代理人电话', key: 'agentPhone', minWidth: 200, sortable: true },
        agentFax: { title: '委托代理人传真', key: 'agentFax', minWidth: 200, sortable: true },
        openingBank: { title: '开户银行', key: 'openingBank', minWidth: 200, sortable: true },
        bankAccount: { title: '银行账号', key: 'bankAccount', minWidth: 200, sortable: true },
        taxRegistrationNumber: { title: '纳税登记号', key: 'taxRegistrationNumber', minWidth: 200, sortable: true },
        phoneNumber: { title: '联系电话', key: 'phoneNumber', minWidth: 200, sortable: true },
        accountBalance: { title: '账户余额', key: 'accountBalance', minWidth: 200, sortable: true },
        availableBalance: { title: '可用余额', key: 'availableBalance', minWidth: 200, sortable: true }
      },
      customerSumColumns: [
        { title: '账户余额合计', key: 'accountBalanceSum', minWidth: 200 },
        { title: '可用余额合计', key: 'availableBalanceSum', minWidth: 200 }
      ],
      customerSumSet: []
    }
  },
  computed: {
    ...mapState('customerStore', [
    ]),
    ...mapGetters('customerStore', [
    ])
  },
  methods: {
    ...mapMutations('customerStore', [
    ]),
    ...mapActions('customerStore', [
      'saveCustomer',
      'updateCustomer',
      'batchRemoveCustomer',
      'getCustomerByPk',
      'queryCustomer',
      'pagingQueryCustomer',
      'getAllCustomer'
    ]),
    addCustomer () {
      this.customerEditModalShow = true
      this.customer = new Customer()
    },
    upCustomer () {
      this.customerEditModalShow = true
      let customerEdit = new Customer()
      customerEdit.customerId = this.customerSelectRow[0].customerId
      this.getCustomerByPk(customerEdit).then(res => {
        if (res.data.success) {
          Object.assign(this.customer, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    toCustomerList () {
      this.customerEditModalShow = false
      this.resetCustomerForm()
      this.getCustomerSet()
    },
    resetCustomerForm () {
      this.$refs.customerForm.resetFields()
    },
    saveCustomerForm () {
      this.customerContinue = false
      this.submitCustomerForm()
    },
    saveAddCustomerForm () {
      this.customerContinue = true
      this.submitCustomerForm()
    },
    submitCustomerForm () {
      this.customerSubmitting = true
      this.$refs.customerForm.validate((valid) => {
        if (valid) {
          if (this.customer.customerId) {
            this.updateCustomer(this.customer).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '客户信息修改成功.', duration: 3 })
                this.resetCustomerForm()
                this.customer = new Customer()
                this.customerSubmitting = false
                if (!this.customerContinue) {
                  this.toCustomerList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.customerSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.customerSubmitting = false }, 3000)
            })
          } else {
            this.saveCustomer(this.customer).then(res => {
              if (res.data.success) {
                this.$Message.success({ content: '客户信息保存成功.', duration: 3 })
                this.resetCustomerForm()
                this.customer = new Customer()
                this.customerSubmitting = false
                if (!this.customerContinue) {
                  this.toCustomerList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                setTimeout(() => { this.customerSubmitting = false }, 3000)
              }
            }).catch(result => {
              this.$Message.error({ content: result.response.statusText, duration: 6 })
              setTimeout(() => { this.customerSubmitting = false }, 3000)
            })
          }
        } else {
          setTimeout(() => { this.customerSubmitting = false }, 3000)
        }
      })
    },
    delCustomer () {
      this.customerSpinShow = true
      this.batchRemoveCustomer(this.customerSelectRow).then(res => {
        if (res.data.success) {
          this.$Message.success({ content: '客户信息删除成功.', duration: 3 })
          this.setCustomerSelectRow([])
          this.getCustomerSet()
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.customerSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.customerSpinShow = false }, 700)
      })
    },
    detailCustomer () {
      this.customerDetailDrawerShow = true
      let customerDetail = new Customer()
      customerDetail.customerId = this.customerSelectRow[0].customerId
      this.getCustomerByPk(customerDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.customer, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    dblclickDetailCustomer (rowData) {
      this.customerDetailDrawerShow = true
      let customerDetail = new Customer()
      customerDetail.customerId = rowData.customerId
      this.getCustomerByPk(customerDetail).then(res => {
        if (res.data.success) {
          Object.assign(this.customer, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    getCustomerSet () {
      this.customerSpinShow = true
      this.customerPageShow = true
      this.pagingQueryCustomer({ customerQuery: {}, rows: this.customerRows, page: this.customerPage }).then(res => {
        if (res.data.success) {
          this.customerSet = res.data.data
          this.customerCount = res.data.count
          this.setCustomerSelectRow([])
          this.queryAllCustomerSum()
          setTimeout(() => { this.customerSpinShow = false }, 700)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.customerSpinShow = false }, 700)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.customerSpinShow = false }, 700)
      })
    },
    keySearchCustomer (keyLikeValue) {
      if (keyLikeValue !== '') {
        this.customerKeySearchSubmitting = true
        this.customerPageShow = false
        this.customerSpinShow = true
        let customerQueryKeySearch = new CustomerQuery()
        customerQueryKeySearch.customerNoAndKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.customerNameOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.creditCodeOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.addressOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.faxOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.legalRepresentativeOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.legalRepresentativePhoneOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.legalRepresentativeFaxOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.agentOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.agentPhoneOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.agentFaxOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.openingBankOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.bankAccountOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.taxRegistrationNumberOrKeyLike = '%' + keyLikeValue + '%'
        customerQueryKeySearch.phoneNumberOrKeyLike = '%' + keyLikeValue + '%'
        this.queryCustomer(customerQueryKeySearch).then(res => {
          if (res.data.success) {
            this.customerSet = res.data.data
            this.customerCount = res.data.count
            this.setCustomerSelectRow([])
            this.queryCustomerSum(customerQueryKeySearch)
            setTimeout(() => { this.customerSpinShow = false }, 700)
            setTimeout(() => { this.customerKeySearchSubmitting = false }, 5000)
          } else {
            this.$Message.error({ content: res.data.msg, duration: 6 })
            setTimeout(() => { this.customerSpinShow = false }, 700)
            setTimeout(() => { this.customerKeySearchSubmitting = false }, 5000)
          }
        }).catch(result => {
          this.$Message.error({ content: result.response.statusText, duration: 6 })
          setTimeout(() => { this.customerSpinShow = false }, 700)
          setTimeout(() => { this.customerKeySearchSubmitting = false }, 5000)
        })
      } else {
        this.customerKeySearchClickRate++
        if (this.customerKeySearchClickRate > 10) {
          this.$Message.warning({ content: '兄dei，你是闲的吗？', duration: 6 })
          this.customerKeySearchClickRate = 0
        }
      }
    },
    vagueQueryCustomer () {
      this.customerQuerySubmitting = true
      this.customerPageShow = false
      this.customerSpinShow = true
      this.customerQueryPoptipShow = false
      this.queryCustomer(this.customerQuery).then(res => {
        if (res.data.success) {
          this.customerSet = res.data.data
          this.customerCount = res.data.count
          this.setCustomerSelectRow([])
          this.queryCustomerSum(this.customerQuery)
          setTimeout(() => { this.customerSpinShow = false }, 700)
          setTimeout(() => { this.customerQuerySubmitting = false }, 5000)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          setTimeout(() => { this.customerSpinShow = false }, 700)
          setTimeout(() => { this.customerQuerySubmitting = false }, 5000)
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
        setTimeout(() => { this.customerSpinShow = false }, 700)
        setTimeout(() => { this.customerQuerySubmitting = false }, 5000)
      })
    },
    resetCustomerQueryForm () {
      this.$refs.customerQueryForm.resetFields()
    },
    setCustomerSelectRow (list) {
      this.customerSelectRow = list
      this.customerSelectRowLength = list.length
      this.customerSum(list)
    },
    customerPageIndexChange (pageIndex) {
      this.customerPage = pageIndex
      this.getCustomerSet()
    },
    customerPageSizeChange (pageSize) {
      this.customerRows = pageSize
      this.customerPage = 1
      this.getCustomerSet()
    },
    customerRefresh () {
      this.customerPage = 1
      this.getCustomerSet()
    },
    changeCustomerColumns () {
      this.customerColumns = []
      this.customerColumnsChecked.forEach(col => this.customerColumns.push(this.customerColumnsNexus[col]))
    },
    customerSum (list) {
      let accountBalanceSum = new Decimal('0')
      let availableBalanceSum = new Decimal('0')
      list.forEach(col => {
        accountBalanceSum = accountBalanceSum.add(new Decimal(col.accountBalance))
        availableBalanceSum = availableBalanceSum.add(new Decimal(col.availableBalance))
      })
      this.customerSumSet = []
      this.customerSumSet.push({ accountBalanceSum: accountBalanceSum.toString(), availableBalanceSum: availableBalanceSum.toString() })
    },
    queryAllCustomerSum () {
      this.getAllCustomer().then(res => {
        if (res.data.success) {
          this.customerSum(res.data.data)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    queryCustomerSum (query) {
      this.queryCustomer(query).then(res => {
        if (res.data.success) {
          this.customerSum(res.data.data)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: result.response.statusText, duration: 6 })
      })
    },
    cleanCustomerColumns () {
      this.customerColumnsChecked = [
        'customerId'
      ]
      this.customerColumns = []
      this.customerColumnsChecked.forEach(col => this.customerColumns.push(this.customerColumnsNexus[col]))
    },
    resetCustomerColumns () {
      this.customerColumnsChecked = [
        'customerId',
        'customerNo',
        'customerName',
        'creditCode',
        'address',
        'fax',
        'legalRepresentative',
        'legalRepresentativePhone',
        'legalRepresentativeFax',
        'agent',
        'agentPhone',
        'agentFax',
        'openingBank',
        'bankAccount',
        'taxRegistrationNumber',
        'phoneNumber',
        'accountBalance',
        'availableBalance'
      ]
      this.customerColumns = []
      this.customerColumnsChecked.forEach(col => this.customerColumns.push(this.customerColumnsNexus[col]))
    }
  },
  mounted () {
    this.changeCustomerColumns()
    this.getCustomerSet()
  }
}
</script>
