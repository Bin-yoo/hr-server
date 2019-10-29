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
                                <Input v-model="formItem.input" clearable placeholder="请输入..." />
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
            <Col span="2"><Button type="primary" @click="addModal = true">添加考核记录</Button></Col>
        </Row>
        <Row>
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="update(index)">编辑</Button>
                    <Button type="error" @click="remove(index)">删除</Button>
                </template>
          </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1"><Button type="error">批量删除</Button></Col>
            <Page :total="100" show-elevator />
        </Row>
        <Modal
            v-model="addModal"
            title="添加员工考核"
            width=30%
            @on-ok="ok"
            @on-cancel="cancel">
            
            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="部门："><!-- 级联菜单1 -->
                            <Select v-model="formItem.department">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位："><!-- 级联菜单2 -->
                            <Select v-model="formItem.position">
                                <Option value="0">教授</Option>
                                <Option value="1">教师</Option>
                                <Option value="2">教务管理人员</Option>
                                <Option value="3">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名："><!-- 级联菜单3 -->
                            <Select v-model="formItem.name">
                                <Option value="0">张三</Option>
                                <Option value="1">李四</Option>
                                <Option value="2">王五</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="考核日期：">
                            <DatePicker type="date" placeholder="选择考核日期" v-model="formItem.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="考核结果：">
                            <Select v-model="formItem.result">
                                <Option value="0">优秀</Option>
                                <Option value="1">良好</Option>
                                <Option value="2">不及格</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="考核内容：">
                            <Input v-model="formItem.content" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <Modal
            v-model="updateModal"
            title="修改员工考核"
            width=30%
            @on-ok="ok"
            @on-cancel="cancel">
            
            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="部门："><!-- 级联菜单1 -->
                            <Select v-model="formItem.department">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="职位："><!-- 级联菜单2 -->
                            <Select v-model="formItem.position">
                                <Option value="0">教授</Option>
                                <Option value="1">教师</Option>
                                <Option value="2">教务管理人员</Option>
                                <Option value="3">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="姓名："><!-- 级联菜单3 -->
                            <Select v-model="formItem.name">
                                <Option value="0">张三</Option>
                                <Option value="1">李四</Option>
                                <Option value="2">王五</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="考核日期：">
                            <DatePicker type="date" placeholder="选择考核日期" v-model="formItem.date"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="考核结果：">
                            <Select v-model="formItem.result">
                                <Option value="0">优秀</Option>
                                <Option value="1">良好</Option>
                                <Option value="2">不及格</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="考核内容：">
                            <Input v-model="formItem.content" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                        <FormItem label="备注：">
                            <Input v-model="formItem.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        </Modal>
    </div>
</template>
<script>
export default{
    data() {
        return {
            addModal: false,
            updateModal: false,
            souFormItem:{
                name: '',       //名字
                gender: '',      //性别
                department: '',  //部门
                position: '',    //职位
                jobLevelID: '',    //职称
                input: '',          //模糊查询
            },
            formItem:{
                name: '',       //名字
                gender:'',      //性别
                department:'',  //部门
                date:'',    //考核日期
                result:'',    //考核结果
                content:'',    //考核内容
                remark:'',    //备注
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
                        title: '日期',
                        key: 'date'
                    },
                    {
                        title: '考核结果',
                        key: 'result'
                    },
                    {
                        title: '考核内容',
                        key: 'content'
                    },
                    {
                        title: '备注',
                        key: 'remarks'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 175,
                        align: 'center'
                    }
                ],
            data1: [
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        result: "优秀",
                        content: "员工技能考评",
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
        update (index) {
            this.updateModal = true;
            console.log(index);
        },
    }
    }
</script>