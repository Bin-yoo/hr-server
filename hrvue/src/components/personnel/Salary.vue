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
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action" >
                    <Button type="primary" style="margin-right: 5px" @click="select(index)">查看调薪记录</Button>
                    <Button type="primary" style="margin-right: 5px" @click="update(index)">调薪</Button>
                    <Button type="error" @click="remove(index)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1">
                <Button type="error">批量删除</Button>
            </Col>
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
                        <FormItem label="部门：">
                            <Input v-model="formItem.grade" placeholder="人事部" disabled></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位：">
                            <Input v-model="formItem.grade" placeholder="工程师" disabled></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名：">
                            <Input v-model="formItem.grade" placeholder="梁彬" disabled></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="基础工资：">
                            <Input v-model="formItem.grade" placeholder="1300"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="绩效工资：">
                            <Input v-model="formItem.season" placeholder="2000"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
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
                <Col span="2"><p>姓名：梁彬</p></Col>
                <Col span="2"><p>工号：1101</p></Col>
            </Row>

            <Row :style="{marginBottom:'10px'}">
                <Table border ref="selection" :columns="columns2" :data="data2" ></Table>
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
                selectModal:false,
                updateModal: false,
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
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '工号',
                        key: 'jobNum'
                    },
                    {
                        title: '部门',
                        key: 'department'
                    },
                    {
                        title: '职位',
                        key: 'position'
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
                data1: [
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        baseSalary:"1200",
                        meritSalary:"2500",
                    },

                ],
                data2:[
                    {
                        befBaseSalary: '1223',
                        afterBaseSalary: "2334",
                        befMeritSalary: "2000",
                        afterMeritSalary: "2300",
                        date:"2019-1-1",
                        remark:"无",
                    },
                ]
            }
        },
        methods: {
            ok() {
                this.$Message.info('Clicked ok');
            },
            cancel() {
                this.$Message.info('Clicked cancel');
            },
            update(index) {
                this.updateModal = true;
                console.log(index);
            },
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            select(index) {
                this.selectModal = true;
                console.log(index);
            },
        }
    }
</script>

