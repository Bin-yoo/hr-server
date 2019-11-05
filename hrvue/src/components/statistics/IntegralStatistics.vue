<template>
    <div>
        <Row>
            <Col>
                <Form :model="souformItem">
                    <Row :gutter="6">
                        <Col span="2">
                            <FormItem>
                                <Select v-model="souFormItem.departmentID" placeholder="部门">
                                    <Option value="0">财务部</Option>
                                    <Option value="1">人事部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="2">
                            <FormItem>
                                <Select v-model="souFormItem.posID" placeholder="职位">
                                    <Option value="0">财务经理</Option>
                                    <Option value="1">人事经理</Option>
                                    <Option value="2">出纳</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="2">
                            <FormItem>
                                <Select v-model="souFormItem.jobLevelID" placeholder="职称">
                                    <Option value="0">高级工程师</Option>
                                    <Option value="1">高级教师</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="3">
                            <FormItem>
                                <Input v-model="souFormItem.input" suffix="ios-search" placeholder="请输入..." style="width: auto" />
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
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="show(index)">查看</Button>
                </template>
          </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="100" show-elevator />
        </Row>
        <Modal
            v-model="showModal"
            title="查看详情"
            width=55%
            @on-ok="ok"
            @on-cancel="cancel">
            
            <Table border ref="selection" :columns="showColumns" :data="showData"></Table>
            <br>
            <Row :style="{textAlign: 'center'}">
                <Page :total="100" show-elevator />
            </Row>
        </Modal>
    </div>
</template>
<script>
    export default{
        data() {
            return {
                showModal: false,
                souFormItem:{
                    name: '',       //名字
                    gender: '',      //性别
                    department: '',  //部门
                    position: '',    //职位
                    jobLevelID: '',    //职称
                    beforeDate: '',    //开始日期
                    afterDate: '',    //结束日期
                    input: '',          //模糊查询
                },
                columns: [
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
                        title: '员工积分',
                        key: 'count'
                    },
                    {
                        title: '在职状态',
                        key: 'state'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 100,
                        align: 'center'
                    }
                ],
                data1: [
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        salary: "1,000.00",
                        integral: "-3",
                        count: "3",
                        state: "在职",
                    },
                ],
                showColumns:[
                    {
                        title: '姓名',
                        width: 80,
                        key: 'name'
                    },
                    {
                        title: '工号',
                        key: 'jobNum'
                    },
                    {
                        title: '部门',
                        width: 80,
                        key: 'department'
                    },
                    {
                        title: '职位',
                        width: 100,
                        key: 'position'
                    },
                    {
                        title: '奖惩日期',
                        key: 'date'
                    },
                    {
                        title: '奖惩类型',
                        width: 90,
                        key: 'category'
                    },
                    {
                        title: '奖惩分数',
                        width: 90,
                        key: 'fraction'
                    },
                    {
                        title: '奖惩原因',
                        key: 'reason'
                    },
                    {
                        title: '备注',
                        key: 'remarks'
                    },
                ],
                showData:[
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                ]
            }
        },
        methods: {
            ok () {
                this.$Message.info('Clicked ok');
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            }, 
            show (index) {
                this.showModal = true;
                console.log(index);
            },
        }
    }
</script>