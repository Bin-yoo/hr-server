<template>
    <div>
        <Row><Col span="3"><h1>员工奖惩管理</h1></Col></Row>
        <Row :style="{margin: '15px 0 0 0'}">
            <Form :model="souformItem">
                <Col span="2" :style="{margin: '0 10px 0 0'}">
                    <FormItem>
                        <Select v-model="formItem.departmentID" placeholder="部门">
                            <Option value="0">财务部</Option>
                            <Option value="1">人事部</Option>
                            <Option value="2">技术部</Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" :style="{margin: '0 10px 0 0'}">
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
                <Col span="3">
                    <FormItem>
                        <Input v-model="formItem.input" suffix="ios-search" placeholder="请输入..." style="width: auto" />
                    </FormItem>
                </Col>
                <Col span="1" :style="{margin: '0 10px 0 0'}">
                    <FormItem>
                        <Button icon="ios-search">搜索</Button>
                    </FormItem>
                </Col>
            </Form>
            <Col span="1"><Button type="primary" @click="addModal = true">添加</Button></Col>
            <Modal
                v-model="addModal"
                title="添加员工调动"
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
                            <FormItem label="奖惩日期：">
                                <DatePicker type="date" placeholder="选择奖惩日期" v-model="formItem.date"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem label="奖惩类型："><!-- 级联菜单1 -->
                                <RadioGroup v-model="formItem.type">
                                        <Radio label="0">奖励</Radio>
                                        <Radio label="1">惩罚</Radio>
                                    </RadioGroup>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="奖惩原因：">
                                <Input v-model="formItem.season" placeholder="请输入..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem label="奖惩分数：">
                                <Input v-model="formItem.grade" placeholder="请输入..."></Input>
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
                title="添加员工调动"
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
                            <FormItem label="奖惩日期：">
                                <DatePicker type="date" placeholder="选择奖惩日期" v-model="formItem.date"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem label="奖惩类型："><!-- 级联菜单1 -->
                                <RadioGroup v-model="formItem.type">
                                        <Radio label="0">奖励</Radio>
                                        <Radio label="1">惩罚</Radio>
                                    </RadioGroup>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="奖惩原因：">
                                <Input v-model="formItem.season" placeholder="请输入..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem label="奖惩分数：">
                                <Input v-model="formItem.grade" placeholder="请输入..."></Input>
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
    </div>
</template>

<script>
export default {
    name: 'PerMv',
    data() {
        return {
            addModal: false,
            updateModal: false,
            formItem:{
                name: '',       //名字
                gender:'',      //性别
                department:'',  //部门
                date:'',    //奖惩日期
                type:'',    //奖惩类型
                season:'',    //奖惩原因
                grade:'',    //奖惩分数
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
                        title: '奖惩类别',
                        key: 'category'
                    },
                    {
                        title: '奖惩原因',
                        key: 'reason'
                    },
                    {
                        title: '奖惩分数',
                        key: 'fraction'
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
        update (index) {
            this.updateModal = true;
            console.log(index);
        },
    }
}
</script>

