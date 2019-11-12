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
                            <Option v-for="item in dropDownList.positionList" :value="item.id" :key="item.id">{{item.name}}</Option>
                        </Select>
                    </Col>
                    <Col span="5">
                        <Input v-model="souformItem.name" clearable placeholder="请输入姓名..." />
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getEmpSalaryList">搜索</Button>
                    </Col>
                </Row>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="employeeSalarylist">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px"
                            @click="select(row.eid, row.employee.name, row.employee.workId)">查看调薪记录
                    </Button>
                    <Button type="primary" style="margin-right: 5px" @click="beforeupdate(index)">调薪</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
        </Row>

        <Modal
            v-model="updateModal"
            title="调薪"
            width=35%
            @on-visible-change="cancel">

            <Form :model="salary" :label-width="80"  :rules="newSalaryRules"  ref="salary">
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
                        <FormItem label="基础工资：" prop="baseSalary">
                            <Input v-model="salary.baseSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="绩效工资：" prop="meritSalary">
                            <Input v-model="salary.meritSalary" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注："  prop="remark">
                            <Input v-model="salary.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                                   placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button @click="handleReset('salary')">重置</Button>
                <Button type="primary" @click="updateSalary('salary')">保存</Button>
            </div>
        </Modal>
        <Modal
            v-model="selectModal"
            title="调薪记录"
            width=60%>

            <Row :style="{margin: '0 0 15px 0'}">
                <Col span="2"><p>姓名：{{name}}</p></Col>
                <Col span="5"><p>工号：{{workId}}</p></Col>
            </Row>

            <Row :style="{marginBottom:'10px'}">
                <Table border ref="selection" :columns="columns2" :data="employeeSalarylogList"></Table>
            </Row>
            <Row>
                <Col>
                    <Page :total="total" show-sizer show-elevator show-total @on-change="onSelectModalPageChange"
                          @on-page-size-change="onSelectModalPageSizeChange"/>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="selectModal = false">返回</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'
    export default {
        components: { Treeselect },
        name: 'PerMv',
        data() {
            return {
                page: 1,
                total: 100,
                limit: 10,
                selectModalPage: 1,
                selectModalTotal: 100,
                selectModalLimit: 10,
                name: '',
                workId: '',
                loading: false,
                selectModal: false,
                updateModal: false,
                index: 1,
                dropDownList: [],
                employeeSalarylist: [],
                employeeSalarylogList: [],
                salary: {
                    id:0,
                    eid: 0,
                    name: '',
                    workId: '',
                    baseSalary: '',
                    meritSalary: '',
                    remark: '',
                },
                newSalary: {
                    id:0, 
                    eid: 0,
                    name: '',
                    workId: '',
                    baseSalary: '',
                    meritSalary: '',
                    remark: '',
                },
                newSalaryRules: {
                    baseSalary: [
                        {required: true, message: '基础工资不能为空', trigger: 'blur' },
                    ],
                    meritSalary: [
                        {required: true, message: '绩效工资不能为空', trigger: 'blur' }
                    ]
                },
                souformItem:{
                    departmentId: null,
                    positionId: null,
                    name: '',
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
                columns2: [
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
            this.getDropDownList();
        },
        watch: {
            page: "getEmpSalaryList",
            limit: "getEmpSalaryList",
            selectModalPage: "select",
            selectModalLimit: "select",
        },
        methods: {
            getDropDownList(){
                this.getRequest("/employee/init").then(resp=> {
                   this.dropDownList = resp.data.data;
                })
            },
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            onSelectModalPageSizeChange(index){
                this.selectModalLimit = index;
            },
            onSelectModalPageChange(index){
                this.selectModalPage = index;
            },
            getEmpSalaryList() {
                this.loading = true;
                this.getRequest("/salary/allEmpSalary", {
                    page: this.page,
                    limit: this.limit,
                    departmentId: this.souformItem.departmentId,
                    positionId: this.souformItem.positionId,
                    name: this.souformItem.name,
                }).then(resp => {
                    console.log(resp)
                    this.loading = false;
                    this.employeeSalarylist = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            updateSalary(name) {
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.salary.baseSalary) || check.test(this.salary.meritSalary)) {
                        this.$Message.error("工资存在特殊字符");
                        return;
                    }

                    if (valid) {
                        this.putRequest("/salary/updateEmpSalary", {
                            id:this.salary.id,
                            remark: this.salary.remark,
                            baseSalary:this.salary.baseSalary,
                            meritSalary:this.salary.meritSalary,
                        }).then(resp => {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getEmpSalaryList();
                                this.newSalary.baseSalary= '';
                                this.newSalary.remark = '';
                                this.newSalary.meritSalary = '';
                                this.$Message.success(resp.data.data);
                                this.updateModal = false;
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            beforeupdate(index) {
                this.updateModal = true;
                this.salary.id = this.employeeSalarylist[index].id;      
                this.salary.eid = this.employeeSalarylist[index].eid;
                this.salary.name = this.employeeSalarylist[index].employee.name;
                this.salary.workId = this.employeeSalarylist[index].employee.workId;
                this.salary.baseSalary = ''+this.employeeSalarylist[index].baseSalary;
                this.salary.meritSalary = ''+this.employeeSalarylist[index].meritSalary;
            },
            select(eid, name, workId) {
                this.selectModal = true;
                this.getRequest("/salary/salary_log/" + eid, {
                    page: this.selectModalPage,
                    limit: this.selectModalLimit,
                }).then(resp => {
                    console.log(resp)
                    this.loading = false;
                    this.employeeSalarylogList = resp.data.data.list;
                    this.name = name;
                    this.workId = workId;
                    this.total = resp.data.data.total;
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
                this.rpList.name = "";
                this.rpList.workId = "";
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['rpList'].resetFields();
                    this.rpList.name = "";
                    this.rpList.workId = "";
                }
            },
        }
    }
</script>

