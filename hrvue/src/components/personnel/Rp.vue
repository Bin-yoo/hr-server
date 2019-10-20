<template>
    <div>
    <Row><Col span="2"><h1>员工调动</h1></Col></Row>
    <Row :style="{margin: '15px 0 0 0'}">
            <Col span="3"><Input suffix="ios-search" placeholder="请输入..." style="width: auto" /></Col>
            <Col span="1"><Button icon="ios-search">搜索</Button></Col>
            <Col span="1" offset="18"><Button type="primary" @click="modal1 = true">添加</Button></Col>
            <Modal
                v-model="modal1"
                title="添加员工调动"
                width=30%
                @on-ok="ok"
                @on-cancel="cancel">
                
                <Form :model="formItem" :label-width="80">
                    <Row>
                        <Col span="12">
                            <FormItem label="姓名：">
                                <Input v-model="formItem.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="性别：">
                                <RadioGroup v-model="formItem.gender">
                                    <Radio label="1">男</Radio>
                                    <Radio label="0">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem label="部门：">
                                <Select v-model="formItem.department">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="职位：">
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
                            <FormItem label="调前部门：">
                                <Select v-model="formItem.beforeDepartment">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="调前职位：">
                                <Select v-model="formItem.beforePosition">
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
                            <FormItem label="调后部门：">
                                <Select v-model="formItem.laterDepartment">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="调后职位：">
                                <Select v-model="formItem.laterPosition">
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
                            <FormItem label="调动日期：">
                                <Select v-model="formItem.transferDate">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="调动原因：">
                                <Input v-model="formItem.transferReason" placeholder="请输入..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">编辑</Button>
                    <Button type="error" size="small" @click="remove(index)">删除</Button>
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
            modal1: false,
            updateModal: false,
            formItem:{
                name: '',       //名字
                gender:'',      //性别
                department:'',  //部门
                position:'',    //职位
                beforeDepartment:'',//调前部门
                beforePosition:'',//调前职位
                laterDepartment:'',//调后部门
                laterPosition:'',//调后职位
                transferDate:'',//调动日期
                transferReason:'',//调动原因
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
                        title: '性别',
                        key: 'sex'
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
                        title: '调前部门',
                        key: 'beforeDepartment'
                    },
                    {
                        title: '调前职位',
                        key: 'beforePosition'
                    },
                    {
                        title: '调后部门',
                        key: 'laterDepartment'
                    },
                    {
                        title: '调后职位',
                        key: 'laterPosition'
                    },
                    {
                        title: '调动日期',
                        key: 'transferDate'
                    },
                    {
                        title: '调动原因',
                        key: 'transferReason'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        align: 'center'
                    }
                ],
            data1: [
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        sex: "男",
                        department: "人事部",
                        position: "人事经理",
                        beforeDepartment: "行政部",
                        beforePosition: "行政经理",
                        laterDepartment: "人事部",
                        laterPosition: "人事经理",
                        transferDate: "调动日期",
                        transferReason: "调动原因",
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
    }
}
</script>

