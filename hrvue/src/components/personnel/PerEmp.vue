<template>
    <div>
        <Row><Col span="2"><h1>员工资料</h1></Col></Row>
        <Row :style="{margin: '15px 0 0 0'}">
            <Col span="3"><Input suffix="ios-search" placeholder="请输入..." style="width: auto" /></Col>
            <Col span="1"><Button icon="ios-search">搜索</Button></Col>
            <Col span="1" offset="18"><Button type="primary" @click="modal1 = true">添加</Button></Col>
            <Modal
                v-model="modal1"
                title="添加员工"
                width=50%
                @on-ok="ok"
                @on-cancel="cancel">

                <Form :model="formItem" :label-width="80">
                    <Row>
                        <Col span="8">
                            <FormItem label="姓名：">
                                <Input v-model="formItem.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="民族：">
                                <Input v-model="formItem.nationID" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="性别：">
                                <RadioGroup v-model="formItem.gender">
                                    <Radio label="1">男</Radio>
                                    <Radio label="0">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="籍贯：">
                                <Input v-model="formItem.nativePlace" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="身份证号：">
                                <Input v-model="formItem.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="出生日期：">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="formItem.birthday"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="政治面貌：">
                                <Select v-model="formItem.politicID">
                                    <Option value="1">群众</Option>
                                    <Option value="2">共青团员</Option>
                                    <Option value="3">中共党员</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="联系方式：">
                                <Input v-model="formItem.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="婚姻状态：">
                                <RadioGroup v-model="formItem.wedlock">
                                    <Radio label="0">未婚</Radio>
                                    <Radio label="1">已婚</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="邮箱：">
                                <Input v-model="formItem.email" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="居住地址：">
                                <Input v-model="formItem.adress" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="所属部门：">
                                <Select v-model="formItem.departmentID">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="职称：">
                                <Select v-model="formItem.departmentID">
                                    <Option value="0">正高级教师</Option>
                                    <Option value="1">高级教师</Option>
                                    <Option value="2">一级教师</Option>
                                    <Option value="3">二级教师</Option>
                                    <Option value="4">三级教师</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="职位：">
                                <Select v-model="formItem.posID">
                                    <Option value="0">教授</Option>
                                    <Option value="1">教师</Option>
                                    <Option value="2">教务管理人员</Option>
                                    <Option value="3">其他</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="学历：">
                                <Select v-model="formItem.titopDegree">
                                    <Option value="0">小学</Option>
                                    <Option value="1">初中</Option>
                                    <Option value="2">高中</Option>
                                    <Option value="3">中专</Option>
                                    <Option value="3">职校</Option>
                                    <Option value="3">专科</Option>
                                    <Option value="3">本科</Option>
                                    <Option value="3">硕士研究生</Option>
                                    <Option value="3">博士研究生</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="毕业院校：">
                                <Input v-model="formItem.school" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="专业：">
                                <Input v-model="formItem.specialty" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="就职日期：">
                                <DatePicker type="date" placeholder="请选择就职日期" v-model="formItem.beginDate"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="就职状态：">
                                <RadioGroup v-model="formItem.workState">
                                    <Radio label="0">在职</Radio>
                                    <Radio label="1">离职</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="转正日期：">
                                <DatePicker type="date" placeholder="请选择转正日期" v-model="formItem.conversionTime"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
            <Modal
                v-model="updateModal"
                title="添加员工"
                width=50%
                @on-ok="ok"
                @on-cancel="cancel">

                <Form :model="formItem" :label-width="80">
                    <Row>
                        <Col span="8">
                            <FormItem label="姓名：">
                                <Input v-model="formItem.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="民族：">
                                <Input v-model="formItem.nationID" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="性别：">
                                <RadioGroup v-model="formItem.gender">
                                    <Radio label="1">男</Radio>
                                    <Radio label="0">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="籍贯：">
                                <Input v-model="formItem.nativePlace" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="身份证号：">
                                <Input v-model="formItem.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="出生日期：">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="formItem.birthday"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="政治面貌：">
                                <Select v-model="formItem.politicID">
                                    <Option value="1">群众</Option>
                                    <Option value="2">共青团员</Option>
                                    <Option value="3">中共党员</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="联系方式：">
                                <Input v-model="formItem.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="婚姻状态：">
                                <RadioGroup v-model="formItem.wedlock">
                                    <Radio label="0">未婚</Radio>
                                    <Radio label="1">已婚</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="邮箱：">
                                <Input v-model="formItem.email" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="居住地址：">
                                <Input v-model="formItem.adress" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="所属部门：">
                                <Select v-model="formItem.departmentID">
                                    <Option value="0">人事部</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="职称：">
                                <Select v-model="formItem.departmentID">
                                    <Option value="0">正高级教师</Option>
                                    <Option value="1">高级教师</Option>
                                    <Option value="2">一级教师</Option>
                                    <Option value="3">二级教师</Option>
                                    <Option value="4">三级教师</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="职位：">
                                <Select v-model="formItem.posID">
                                    <Option value="0">教授</Option>
                                    <Option value="1">教师</Option>
                                    <Option value="2">教务管理人员</Option>
                                    <Option value="3">其他</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="学历：">
                                <Select v-model="formItem.titopDegree">
                                    <Option value="0">小学</Option>
                                    <Option value="1">初中</Option>
                                    <Option value="2">高中</Option>
                                    <Option value="3">中专</Option>
                                    <Option value="3">职校</Option>
                                    <Option value="3">专科</Option>
                                    <Option value="3">本科</Option>
                                    <Option value="3">硕士研究生</Option>
                                    <Option value="3">博士研究生</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="毕业院校：">
                                <Input v-model="formItem.school" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="专业：">
                                <Input v-model="formItem.specialty" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="就职日期：">
                                <DatePicker type="date" placeholder="请选择就职日期" v-model="formItem.beginDate"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="8">
                            <FormItem label="就职状态：">
                                <RadioGroup v-model="formItem.workState">
                                    <Radio label="0">在职</Radio>
                                    <Radio label="1">离职</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                        <Col span="8">
                            <FormItem label="转正日期：">
                                <DatePicker type="date" placeholder="请选择转正日期" v-model="formItem.conversionTime"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" size="small" style="margin-right: 5px" @click="update(index)">编辑</Button>
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
    name: 'PerEmp',
    data() {
        return {
            modal1: false,
            updateModal: false,
            formItem:{
                name: '',       //名字
                nationID:'',    //民族
                gender:'',      //性别
                politicID:'',   //政治面貌
                nativePlace:'', //籍贯地
                birthday:'',    //出生日期
                idCard:'',      //身份证号
                wedlock:'',     //婚姻状态
                phone:'',       //联系方式
                email:'',       //邮箱
                adress:'',      //居住地址
                departmentID:'',//所属部门
                jobLevelID:'',  //职称
                posID:'',       //职位
                titopDegree:'', //学历
                school:'',      //毕业学院
                specialty:'',   //专业
                beginDate:'',   //就职日期
                workState:'',   //就职状态（在职or离职）
                conversionTime:'',//转正日期
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
                        title: '性别',
                        key: 'sex'
                    },
                    {
                        title: '出生日期',
                        key: 'date'
                    },
                    {
                        title: '籍贯',
                        key: 'nativePlace'
                    },
                    {
                        title: '联系方式',
                        key: 'phone'
                    },
                    {
                        title: '电子邮箱',
                        key: 'email'
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
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },

                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },{
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        sex: "男",
                        date: "2019年10月16日",
                        nativePlace: "广西",
                        phone: "12345678910",
                        email: "12345678@qq.com",
                    },
                ],
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
