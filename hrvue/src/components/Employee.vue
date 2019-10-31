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
            <Col span="2"><Button type="primary" @click="addModal = true">添加档案</Button></Col>
        </Row>
        <Row>
            <Table border ref="selection" :columns="columns" :data="data1">
                <template slot-scope="{ row, index }" slot="action">
                    <Button style="margin-right: 5px" @click="show(index)">查看</Button>
                    <Button type="primary" style="margin-right: 5px" @click="update(index)">编辑</Button>
                    <Button type="error" @click="remove(index)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Col span="1"><Button type="error">批量删除</Button></Col>
            <Row :style="{textAlign: 'center'}">
                <Page :total="100" show-elevator />
            </Row>
        </Row>
        <Modal
            v-model="addModal"
            title="添加员工档案"
            width=50%
            @on-ok="ok"
            @on-cancel="cancel">

            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：">
                                <Input v-model="formItem.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：">
                                <RadioGroup v-model="formItem.gender">
                                    <Radio label="1">男</Radio>
                                    <Radio label="0">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：">
                                <Input v-model="formItem.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：">
                                <Select v-model="formItem.politicID">
                                    <Option value="1">群众</Option>
                                    <Option value="2">共青团员</Option>
                                    <Option value="3">中共党员</Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：">
                                <Input v-model="formItem.nationID" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：">
                                <RadioGroup v-model="formItem.wedlock">
                                    <Radio label="0">未婚</Radio>
                                    <Radio label="1">已婚</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="formItem.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：">
                                <Input v-model="formItem.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img :src="pictureItem.url" :style="{border:'0.2px solid black',width:'128px',height:'166px'}">
                        </Row>
                        <Row>
                            <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :default-file-list="defaultList"
                                :on-success="handleSuccess"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUpload"
                                multiple
                                type="drag"
                                action="//jsonplaceholder.typicode.com/posts/"
                                style="display: inline-block;width:128px;">

                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：">
                            <Input v-model="formItem.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
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
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="所属部门：">
                            <Select v-model="formItem.departmentID">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
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
                </Row>
                <Row>
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
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="就职日期：">
                            <DatePicker type="date" placeholder="请选择就职日期" v-model="formItem.beginDate"></DatePicker>
                        </FormItem>
                    </Col>
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
            title="编辑员工档案"
            width=50%
            @on-ok="ok"
            @on-cancel="cancel">

            <Form :model="formItem" :label-width="80">
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：">
                                <Input v-model="formItem.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：">
                                <RadioGroup v-model="formItem.gender">
                                    <Radio label="1">男</Radio>
                                    <Radio label="0">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：">
                                <Input v-model="formItem.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：">
                                <Select v-model="formItem.politicID">
                                    <Option value="1">群众</Option>
                                    <Option value="2">共青团员</Option>
                                    <Option value="3">中共党员</Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：">
                                <Input v-model="formItem.nationID" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：">
                                <RadioGroup v-model="formItem.wedlock">
                                    <Radio label="0">未婚</Radio>
                                    <Radio label="1">已婚</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="formItem.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：">
                                <Input v-model="formItem.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img :src="pictureItem.url" :style="{border:'0.2px solid black',width:'128px',height:'166px'}">
                        </Row>
                        <Row>
                            <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :default-file-list="defaultList"
                                :on-success="handleSuccess"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUpload"
                                multiple
                                type="drag"
                                action="//jsonplaceholder.typicode.com/posts/"
                                style="display: inline-block;width:128px;">

                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：">
                            <Input v-model="formItem.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
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
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="所属部门：">
                            <Select v-model="formItem.departmentID">
                                <Option value="0">人事部</Option>
                                <Option value="1">财务部</Option>
                                <Option value="2">技术部</Option>
                            </Select>
                        </FormItem>
                    </Col>
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
                </Row>
                <Row>
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
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="就职日期：">
                            <DatePicker type="date" placeholder="请选择就职日期" v-model="formItem.beginDate"></DatePicker>
                        </FormItem>
                    </Col>
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
            v-model="showModal"
            title="员工档案详情"
            @on-ok="ok"
            width=40%
            @on-cancel="cancel">
            <Row>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>姓名：</p></Col>
                        <Col span="12"><p>宇哥</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>性别：</p></Col>
                        <Col span="12"><p>男</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>身份证号码：</p></Col>
                        <Col span="12"><p>45088119984564716X</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>政治面貌：</p></Col>
                        <Col span="12"><p>群众</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>籍贯：</p></Col>
                        <Col span="12"><p>广西南宁市</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>民族：</p></Col>
                        <Col span="12"><p>汉族</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>婚姻状态：</p></Col>
                        <Col span="12"><p>未婚</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>出生日期：</p></Col>
                        <Col span="12"><p>2019年10月16日</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>联系方式：</p></Col>
                        <Col span="12"><p>12345678910</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>邮箱：</p></Col>
                        <Col span="12"><p>12345678910@qq.com</p></Col>
                    </Row>
                </Col>
                <Col span="5">
                    <img :src="pictureItem.url" :style="{border:'0.2px solid black',width:'120px',height:'150px',margin:'0 0 0 20px'}">
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>居住地址：</p></Col>
                        <Col span="12"><p>广西省南宁市西乡塘区大学西路169号</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>所属部门：</p></Col>
                        <Col span="12"><p>财务部</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>职称：</p></Col>
                        <Col span="12"><p>高级工程师</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>职位：</p></Col>
                        <Col span="12"><p>财政经理</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>学历：</p></Col>
                        <Col span="12"><p>大专</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>毕业院校：</p></Col>
                        <Col span="12"><p>南宁职业技术学院</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>专业：</p></Col>
                        <Col span="12"><p>软件技术</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>就职日期：</p></Col>
                        <Col span="12"><p>2019年10月16日</p></Col>
                    </Row>
                </Col>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>就职状态：</p></Col>
                        <Col span="12"><p>在职</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="8"><p>转正日期：</p></Col>
                        <Col span="12"><p>2019年10月16日</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Tabs type="card">
                    <TabPane label="奖惩资料">
                        <Table :columns="rewPunColumns" :data="rewPunData"></Table>
                        <Row justify="center" align="middle" :style="{margin: '10px 0 0 0'}">
                            <Col  offset="5"><Page :total="100" show-elevator /></Col>
                        </Row>
                    </TabPane>
                    <TabPane label="考核资料">
                        <Table :columns="assColumns" :data="assData"></Table>
                        <Row justify="center" align="middle" :style="{margin: '10px 0 0 0'}">
                            <Col  offset="5"><Page :total="100" show-elevator /></Col>
                        </Row>
                    </TabPane>
                    <TabPane label="调动资料">
                        <Table :columns="transferColumns" :data="transferdata"></Table>
                        <Row justify="center" align="middle" :style="{margin: '10px 0 0 0'}">
                            <Col  offset="5"><Page :total="100" show-elevator /></Col>
                        </Row>
                    </TabPane>
                </Tabs>
            </Row>
        </Modal>
    </div>
</template>

<script>
export default {
    name: 'PerEmp',
    data() {
        return {
            addModal: false,
            updateModal: false,
            showModal: false,
            pictureItem:{
                name: '',
                url: 'C:\Users\wei\Pictures\Camera Roll\84353b61ly1g80i5t5dn8j21910u0k1z.jpg',
            },
            souformItem:{
                departmentID: '',
                posID: '',
                jobLevelID: '',
            },
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
                    key: 'name',
                    width: 90
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
                    title: '职称',
                    key: 'jobLevelID'
                },
                {
                    title: '性别',
                    key: 'gender',
                    width: 65
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
                    width: 230,
                    align: 'center'
                }
            ],
            data1: [
                {
                    name: '宇哥',
                    jobNum: 20191016001,
                    department: "人事部",
                    position: "人事部经理",
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
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
                    jobLevelID: "无职称",
                    gender: "男",
                    date: "2019年10月16日",
                    nativePlace: "广西",
                    phone: "12345678910",
                    email: "12345678@qq.com",
                },
                
            ],
            rewPunColumns: [
                {
                    title: '奖惩日期',
                    key: 'date'
                },
                {
                    title: '奖惩类型',
                    key: 'type'
                },
                {
                    title: '奖惩分数',
                    key: 'grade'
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
            rewPunData: [
                {
                    date: "2019年10月21日",
                    type: "惩罚",
                    grade: "-1",
                    reason: "迟到",
                    remark: "迟到",
                },
            ],
            assColumns: [
                {
                    title: '考核日期',
                    key: 'date'
                },
                {
                    title: '考核内容',
                    key: 'content'
                },
                {
                    title: '考核结果',
                    key: 'result'
                },
                {
                    title: '备注',
                    key: 'remark'
                },
            ],
            assData: [
                {
                    date: "2019年10月21日",
                    content: "带薪拉屎",
                    result: "完成",
                    remark: "完成",
                },
            ],
            transferColumns: [
                {
                    title: '调动日期',
                    key: 'date'
                },
                {
                    title: '调前部门',
                    key: 'beforeDepar'
                },
                {
                    title: '调前职位',
                    key: 'beforePos'
                },
                {
                    title: '调动原因',
                    key: 'reason'
                },
                {
                    title: '调后部门',
                    key: 'afterDepar'
                },
                {
                    title: '调后职位',
                    key: 'afterPos'
                },
                {
                    title: '备注',
                    key: 'remark'
                },
            ],
            transferdata: [
                {
                    date: "2019年10月21日",
                    beforeDepar: "财务部",
                    beforePos: "财务经理",
                    reason: "工作需要",
                    afterDepar: "技术部",
                    afterPos: "项目经理",
                    remark: "项目需要",
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
        show(index) {
            this.showModal = true;
            console.log(index);
        }
    }
}
</script>


<style>
.a{border:1px solid black; }
</style>
