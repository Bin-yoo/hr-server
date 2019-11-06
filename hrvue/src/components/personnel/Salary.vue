<template>
    <div>
        <Row>
            <Col span="22">
                <Form :model="souformItem">
                    <Row :gutter="6">
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.departmentID" placeholder="部门">
                                    <Option value="0">财务部</Option>
                                    <Option value="1">人事部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.posID" placeholder="职位">
                                    <Option value="0">财务经理</Option>
                                    <Option value="1">人事经理</Option>
                                    <Option value="2">出纳</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.jobLevelID" placeholder="职称">
                                    <Option value="0">高级工程师</Option>
                                    <Option value="1">高级教师</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="5">
                            <FormItem>
                                <Input v-model="formItem.input" clearable placeholder="请输入..."/>
                            </FormItem>
                        </Col>
                        <Col span="1">
                            <FormItem>
                                <Button icon="ios-search">搜索</Button>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Col>
        </Row>
        <Row>
            <Table border ref="selection" :columns="columns" :data="employeeSalarylist">
                <template slot-scope="{ row, index }" slot="action" >
                    <Button type="primary" style="margin-right: 5px" @click="select(row.eid, row.employee.name, row.employee.workId)">查看调薪记录</Button>
                    <Button type="primary" style="margin-right: 5px" @click="beforeupdate(index)">调薪</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="100" show-elevator/>
        </Row>

        <Modal
                v-model="updateModal"
                title="调薪"
                width=30%
                @on-ok="ok"
                @on-cancel="cancel">

            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="姓名：">
                            <p>{{salary.name}}</p>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="工号：">
                            <p>{{salary.workId}}</p>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="基础工资：">
                            <Input v-model="salary.baseSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="绩效工资：">
                            <Input v-model="salary.meritSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="salary.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                                   placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <Modal
                v-model="selectModal"
                title="调薪记录"
                width=60%
                @on-ok="ok"
                @on-cancel="cancel">
            <Row :style="{margin: '0 0 15px 0'}">
                <Col span="2"><p>姓名：{{name}}</p></Col>
                <Col span="5"><p>工号：{{workId}}</p></Col>
            </Row>

            <Row :style="{marginBottom:'10px'}">
                <Table border ref="selection" :columns="columns2" :data="employeeSalarylogList" ></Table>
            </Row>
            <Row>
                <Col>
                    <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
                </Col>
            </Row>
        </Modal>
    </div>
</template>

<script>

    export default {
        name: 'PerMv',
        data() {
            return {
                page:1,
                total: 100,
                limit: 10,
                name:'',
                workId:'',
                loading: false,
                selectModal:false,
                updateModal: false,
                index: 1,
                employeeSalarylist:[

                ],
                employeeSalarylogList:[

                ],
                salary:{
                    eid:0,
                    name:'',
                    workId:'',
                    baseSalary:'',
                    meritSalary:'',
                    remark:'',
                },
                souFormItem: {
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    position: '',    //职位
                    jobLevelID: '',    //职称
                    input: '',          //模糊查询
                },
                formItem: {
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    date: '',    //奖惩日期
                    type: '',    //奖惩类型
                    season: '',    //奖惩原因
                    grade: '',    //奖惩分数
                    remark: '',    //备注
                },
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
                        title: '基础工资',
                        key: 'baseSalary'
                    },
                    {
                        title: '绩效工资',
                        key: 'meritSalary'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 280,
                        align: 'center'
                    }
                ],
                columns2:[
                    {
                        title: '修改前基础工资',
                        key: 'befBaseSalary'
                    },
                    {
                        title: '修改后基础工资',
                        key: 'afterBaseSalary'
                    },
                    {
                        title: '修改前绩效工资',
                        key: 'befMeritSalary'
                    },
                    {
                        title: '修改后绩效工资',
                        key: 'afterMeritSalary'
                    },
                    {
                        title: '修改日期',
                        key: 'date'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    }
                ],
            }
        },
        mounted: function () {
            this.getEmpSalaryList();
        },
        methods: {
            getEmpSalaryList(){
                this.loading = true;
                this.getRequest("/salary/allEmpSalary",{
                    page: this.page,
                    limit: this.limit,
                    name: this.keyword,
                }).then(resp=> {
                    console.log(resp)
                    this.loading = false;
                    this.employeeSalarylist = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            ok() {
                this.$Message.info('Clicked ok');
            },
            cancel() {
                this.$Message.info('Clicked cancel');
            },
            beforeupdate(index) {
                this.updateModal = true;
                this.salary.eid = this.employeeSalarylist[index].eid;
                this.salary.name = this.employeeSalarylist[index].employee.name;
                this.salary.workId = this.employeeSalarylist[index].employee.workId;
                this.salary.baseSalary = this.employeeSalarylist[index].baseSalary;
                this.salary.meritSalary = this.employeeSalarylist[index].meritSalary;
            },
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            select(eid,name,workId) {
                this.selectModal = true;
                console.log(eid);
                this.getRequest("/salary/salary_log/"+eid,{
                    page: this.page,
                    limit: this.limit,
                }).then(resp=> {
                    console.log(resp)
                    this.loading = false;
                    this.employeeSalarylogList = resp.data.data.list;
                    this.name=name;
                    this.workId=workId;
                    this.total = resp.data.data.total;
                })
            },
        }
    }
</script>

