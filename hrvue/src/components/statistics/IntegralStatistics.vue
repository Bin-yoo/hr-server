<template>
    <div>
        <Row>
            <Col span="22">
                <Row :gutter="6">
                    <Col span="3">
                        <treeselect class="departDown" v-model="souformItem.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>
                    </Col>
                    <Col span="2">
                        <Select v-model="souformItem.positionId" placeholder="职位" clearable>
                            <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id">
                                {{item.name}}
                            </Option>
                        </Select>
                    </Col>
                    <Col span="5">
                        <Input v-model="souformItem.name" clearable placeholder="请输入姓名..."/>
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getEmpIntegerList">搜索</Button>
                    </Col>
                </Row>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="empIntegerList">
                <template slot-scope="{ row }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="getEmpRpList(row.eid)">查看奖惩记录</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="total" show-sizer show-total @on-change="pageChange"
                  @on-page-size-change="onPageSizeChange"/>
        </Row>
        <Modal
            v-model="showModal"
            title="查看详情"
            width=55%
            @on-ok="ok"
            @on-cancel="cancel">

            <Table border ref="selection" :columns="showColumns" :data="empRpList">
            </Table>
            <br>
            <Row :style="{textAlign: 'center'}">
                <Page :total="total" show-sizer show-elevator show-total @on-change="onShowModalPageChange"
                      @on-page-size-change="onShowModalPageSizeChange"/>
            </Row>
        </Modal>
    </div>
</template>
<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'

    export default {
        components: {Treeselect},
        data() {
            return {
                showModal: false,
                page: 1,
                total: 100,
                limit: 10,
                showModalPage: 1,
                showModalTotal: 100,
                showModalLimit: 10,
                dropDownList: [],
                souformItem: {
                    departmentId: null,
                    positionId: null,
                    name: '',
                },
                souFormItem: {
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    position: '',    //职位
                    jobLevelID: '',    //职称
                    beforeDate: '',    //开始日期
                    afterDate: '',    //结束日期
                    input: '',          //模糊查询
                },
                empIntegerList: [],
                empRpList: [],
                columns: [
                    {
                        title: '姓名',
                        key: 'name',
                        render: (h, params) => {
                            return h('span', params.row.employee.name);
                        }
                    },
                    {
                        title: '工号',
                        key: 'workId',
                        render: (h, params) => {
                            return h('span', params.row.employee.workId);
                        }
                    },
                    {
                        title: '部门',
                        key: 'departmentName',
                        render: (h, params) => {
                            return h('span', params.row.employee.departmentName);
                        }
                    },
                    {
                        title: '职位',
                        key: 'positionName',
                        render: (h, params) => {
                            return h('span', params.row.employee.positionName);
                        }
                    },
                    {
                        title: '员工积分',
                        key: 'integral'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 140,
                        align: 'center'
                    }
                ],
                showColumns: [
                    {
                        title: '姓名',
                        width: 80,
                        key: 'name',
                        render: (h, params) => {
                            return h('span', params.row.employee.name);
                        }
                    },
                    {
                        title: '工号',
                        key: 'workId',
                        render: (h, params) => {
                            return h('span', params.row.employee.workId);
                        }
                    },
                    {
                        title: '部门',
                        width: 80,
                        key: 'departmentName',
                        render: (h, params) => {
                            return h('span', params.row.employee.departmentName);
                        }
                    },
                    {
                        title: '职位',
                        width: 100,
                        key: 'positionName',
                        render: (h, params) => {
                            return h('span', params.row.employee.positionName);
                        }
                    },
                    {
                        title: '日期',
                        key: 'date'
                    },
                    {
                        title: '奖惩类型',
                        width: 80,
                        key: 'type'
                    },
                    {
                        title: '奖惩分数',
                        width: 80,
                        key: 'point'
                    },
                    {
                        title: '奖惩原因',
                        key: 'reason'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                ],
            }
        },
        watch: {
            page: "getEmpIntegerList",
            limit: "getEmpIntegerList",
            showModalLimit:"getEmpRpList",
            onShowModalPageChange:"getEmpRpList",
        },
        mounted() {
            this.getEmpIntegerList();
            this.getDropDownList();
        },
        methods: {
            getDropDownList() {
                this.getRequest("/employee/init").then(resp => {
                    this.dropDownList = resp.data.data;
                })
            },
            pageChange(index) {
                this.page = index;
            },
            onPageSizeChange(index) {
                this.limit = index;
            },
            onShowModalPageSizeChange(index){
                this.showModalLimit = index;
            },
            onShowModalPageChange(index){
                this.showModalPage = index;
            },
            getEmpRpList(eid) {
                this.showModal = true;
                this.getRequest("/rp/empRp/" + eid, {
                    page: this.page,
                    limit: this.limit,
                }).then(resp => {
                    console.log(eid)
                    console.log(resp)
                    this.empRpList = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            getEmpIntegerList() {
                this.getRequest("/integral/allIntegral", {
                    page: this.page,
                    limit: this.limit,
                    departmentId: this.souformItem.departmentId,
                    positionId: this.souformItem.positionId,
                    name: this.souformItem.name,
                }).then(resp => {
                    this.empIntegerList = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            ok() {
                this.$Message.info('Clicked ok');
            },
            cancel() {
                this.$Message.info('Clicked cancel');
            },
        }
    }
</script>