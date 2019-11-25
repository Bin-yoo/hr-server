<template>
    <div>
        <div v-if="this.$store.state.user.eid == null">
            <h1>你登录的是系统测试用户</h1>
        </div>
        <div v-else>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>姓名：</p></Col>
                        <Col span="11"><p>{{perEmpList.name}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>性别：</p></Col>
                        <Col span="11"><p>{{perEmpList.sex}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>身份证号码：</p></Col>
                        <Col span="11"><p>{{perEmpList.idCard}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>政治面貌：</p></Col>
                        <Col span="11"><p>{{perEmpList.politiclName}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>籍贯：</p></Col>
                        <Col span="11"><p>{{perEmpList.nativePlace}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>工号：</p></Col>
                        <Col span="11"><p>{{perEmpList.workId}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>民族：</p></Col>
                        <Col span="11"><p>{{perEmpList.nationName}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>婚姻状态：</p></Col>
                        <Col span="11"><p>{{perEmpList.wedlock}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>出生日期：</p></Col>
                        <Col span="11"><p>{{birthday}}</p></Col>
                    </Row>
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>联系方式：</p></Col>
                        <Col span="11"><p>{{perEmpList.phone}}</p></Col>
                    </Row>
                </Col>
                <Col span="2">
                    <img src="../../assets/avatar.png" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-if="perEmpList.picture=='' || perEmpList.picture==null ? true : false">
                            <img :src="perEmpList.picture" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-else>
                </Col>
            </Row>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>邮箱：</p></Col>
                        <Col span="11"><p>{{perEmpList.email}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>居住地址：</p></Col>
                        <Col span="11"><p>{{perEmpList.address}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>学历：</p></Col>
                        <Col span="11"><p>{{perEmpList.titopDegree}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>毕业院校：</p></Col>
                        <Col span="11"><p>{{perEmpList.school}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>专业：</p></Col>
                        <Col span="11"><p>{{perEmpList.specialty}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Divider :style="{margin: '0 0 24px 0'}" />
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>所属部门：</p></Col>
                        <Col span="11"><p>{{perEmpList.departmentName}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>职位：</p></Col>
                        <Col span="11"><p>{{perEmpList.positionName}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>职称：</p></Col>
                        <Col span="11"><p>{{perEmpList.jobLevelName}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>就职日期：</p></Col>
                        <Col span="11"><p>{{perEmpList.beginDate}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>在职状态：</p></Col>
                        <Col span="11"><p>{{perEmpList.workState}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>转正日期：</p></Col>
                        <Col span="11"><p>{{perEmpList.conversionTime}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>是否正式员工：</p></Col>
                        <Col span="11"><p>{{perEmpList.conversionTime == null||'' ? "实习" : "正式"}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>合同起始日期：</p></Col>
                        <Col span="11"><p>{{perEmpList.beginContract}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>合同结束日期：</p></Col>
                        <Col span="11"><p>{{perEmpList.endContract}}</p></Col>
                    </Row>
                </Col>
                <Col span="4">
                    <Row :style="{margin: '0 0 15px 0'}">
                        <Col span="9"><p>离职日期：</p></Col>
                        <Col span="11"><p>{{perEmpList.quitTime}}</p></Col>
                    </Row>
                </Col>
            </Row>
            <Row :gutter="16">
                <Col span="1">
                    <Button type="primary" @click="beforeUpdate">修改资料</Button>
                </Col>
                <Col span="2">
                    <Button type="primary" @click="passwordModal = true">修改密码</Button>
                </Col>
            </Row>
            <br>
            <Row>
                <Tabs type="card">
                    <TabPane label="奖惩资料">
                        <Table :columns="rewPunColumns" :data="empRpLists"></Table>
                        <Row type="flex" justify="center"  :style="{margin: '10px 0 0 0'}">
                            <Col><Page :total="rpTotal" :page-size="rpPageSize" show-elevator show-total @on-change="rpPageChange" @on-page-size-change="onRpPageSizeChange"/></Col>
                        </Row>
                    </TabPane>
                    <TabPane label="考核资料">
                        <Table border ref="selection" :columns="departAssColumns" :data="departAssLists">
                            <template slot-scope="{ row }" slot="commit">
                                <p v-if="row.commit">已提交</p>
                                <p v-else>未提交</p>
                            </template>
                        </Table>
                        <Row type="flex" justify="center"  :style="{margin: '10px 0 0 0'}">
                            <Page :total="departAssTotal" :page-size="departAssPageSize" show-elevator show-total @on-change="departAssPageChange"/>            
                        </Row>
                    </TabPane>
                </Tabs>
            </Row>
        </div>
        <Modal
            v-model="updateModal"
            title="编辑员工档案"
            width=55%>

            <Form ref="perEmpUpdateList" :model="perEmpUpdateList" :rules="newEmployeeRules" :label-width="100" >
                <Row>
                    <Col span="8">
                        <Row>
                            <FormItem label="姓名：" prop="name">
                                <Input v-model="perEmpUpdateList.name" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="性别：" prop="sex">
                                <RadioGroup v-model="perEmpUpdateList.sex">
                                    <Radio label="男">男</Radio>
                                    <Radio label="女">女</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="身份证号：" prop="idCard">
                                <Input v-model="perEmpUpdateList.idCard" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="政治面貌：" prop="politiclId">
                                <Select v-model="perEmpUpdateList.politiclId">
                                    <Option v-for="item in dropDownList.politicalStatusList" :value="item.id" :key="item.id"  :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="8">
                        <Row>
                            <FormItem label="民族：" prop="nationId">
                                <Select v-model="perEmpUpdateList.nationId" clearable>
                                    <Option v-for="item in dropDownList.nationList" :value="item.id" :key="item.id" :label="item.name"></Option>
                                </Select>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="婚姻状态：" prop="wedlock">
                                <RadioGroup v-model="perEmpUpdateList.wedlock">
                                    <Radio label="未婚">未婚</Radio>
                                    <Radio label="已婚">已婚</Radio>
                                    <Radio label="离异">离异</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="出生日期：" prop="birthday">
                                <DatePicker type="date" placeholder="选择出生日期" v-model="perEmpUpdateList.birthday"></DatePicker>
                            </FormItem>
                        </Row>
                        <Row>
                            <FormItem label="联系方式：" prop="phone">
                                <Input v-model="perEmpUpdateList.phone" placeholder="请输入"></Input>
                            </FormItem>
                        </Row>
                    </Col>
                    <Col span="4" offset="2">
                        <Row>
                            <img src="../../assets/avatar.png" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-if="perEmpUpdateList.picture=='' || perEmpUpdateList.picture==null ? true : false">
                            <img :src="perEmpUpdateList.picture" :style="{border:'0.2px solid black',width:'128px',height:'166px'}" v-else>
                        </Row>
                        <Row>
                            <Upload
                                ref="upload"
                                name='picture'
                                :show-upload-list="false"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-success="uploadSuccess"
                                :headers="headers"
                                multiple
                                type="drag"
                                action="http://localhost:8080/hrserver/employee/picture"
                                style="display: inline-block;width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传图片</Button>
                            </Upload>
                        </Row>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="籍贯：" prop="nativePlace">
                            <Input v-model="perEmpUpdateList.nativePlace" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="邮箱：" prop="email">
                            <Input v-model="perEmpUpdateList.email" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="居住地址：" prop="address">
                            <Input v-model="perEmpUpdateList.address" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                        <FormItem label="学历：" prop="titopDegree">
                            <Select v-model="perEmpUpdateList.titopDegree">
                                <Option value="小学">小学</Option>
                                <Option value="初中">初中</Option>
                                <Option value="高中">高中</Option>
                                <Option value="大专">大专</Option>
                                <Option value="本科">本科</Option>
                                <Option value="硕士">硕士</Option>
                                <Option value="博士">博士</Option>
                                <Option value="其他">其他</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="毕业院校：" prop="school">
                            <Input v-model="perEmpUpdateList.school" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem label="专业：" prop="specialty">
                            <Input v-model="perEmpUpdateList.specialty" placeholder="请输入"></Input>
                        </FormItem>
                    </Col> 
                </Row>
            </Form>
            <div slot="footer">
                <Button type="primary" @click="update('perEmpUpdateList')">保存</Button>
            </div>
        </Modal>
        <Modal
            v-model="passwordModal"
            title="修改密码"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="password" :rules="passwordRules" :label-width="80" ref="password">
                        <FormItem label="旧密码:" prop="oldpassword">
                            <Input v-model="password.oldpassword" type="password" placeholder="新密码"></Input>
                        </FormItem>
                        <FormItem label="新密码:" prop="newpassword">
                            <Input v-model="password.newpassword" type="password" placeholder="新密码"></Input>
                        </FormItem>
                        <FormItem label="重复密码:" prop="strPassword">
                            <Input v-model="password.strPassword" type="password" placeholder="确认新密码"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openResetPsw=false">取消</Button>
                <Button type="primary" @click="resetPsw('password')">保存</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import moment from "moment"
    export default {
        name: 'PerEmp',
        data() {
            return {
                headers: {
                    'token': this.$store.state.token,
                    'sessionId': this.$store.state.sessionId,
                },
                updateModal: false,
                passwordModal : false,
                rpPage:1,
                rpTotal: 100,
                rpLimit: 4,
                rpPageSize: 5,
                departAssPage: 1,
                departAssTotal: 100,
                departAssLimit: 5,
                departAssPageSize: 5,
                birthday: '',
                dropDownList: [],
                rpByIdLists: [],
                departAssLists: [],
                perEmpList: {},
                perEmpUpdateList:{
                    id: '',
                    workId: '',
                    name: '',
                    sex: '',
                    nationId: '',
                    nationName: '',
                    birthday: '',
                    politiclId: '',
                    politiclName: '',
                    picture: '',
                    wedlock: '',
                    nativePlace: '',
                    idCard: '',
                    email: '',
                    phone: '',
                    address: '',
                    departmentId: '',
                    departmentName: '',
                    positionId: '',
                    positionName: '',
                    jobLevelId: '',
                    jobLevelName: '',
                    titopDegree: '',
                    specialty: '',
                    school: '',
                    beginDate: '',
                    workState: '',
                    conversionTime: '',
                    quitTime: '',
                    beginContract: '',
                    endContract: '',
                    baseSalary: '',
                },
                password: {
                    oldpassword: '',
                    newpassword: '',
                    strPassword: '',
                },
                formatDate:{   //转化后日期临时存储
                    birthday:'',
                    beginDate:'',
                    conversionTime:'',
                    beginContract:'',
                    endContract:'',
                    quitTime:'',
                },
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
                departAssColumns: [
                    {
                        title: '考核名称',
                        key: 'name'
                    },
                    {
                        title: '状态',
                        key: 'state'
                    },
                    {
                        title: '创建时间',
                        key: 'createDate'
                    },
                    {
                        title: '开始时间',
                        key: 'beginDate'
                    },
                    {
                        title: '结束时间',
                        key: 'endDate'
                    },
                    {
                        title: '是否提交',
                        slot: 'commit'
                    },
                    {
                        title: '备注',
                        key: 'remarks'
                    },
                ],
                empRpLists: [],
                rewPunData: [],
                user: this.$store.state.user,
                newEmployeeRules: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    idCard: [
                        {required: true, message: '身份证不能为空', trigger: 'blur'},
                        {type: 'string', min: 16, message: '身份证不能少于16位'},
                        {type: 'string', max: 18, message: '身份证不能大于18位'},
                    ],
                    politiclId: [
                        {required: true, type: 'number', message: '请选择政治面貌', trigger: 'change'}
                    ],
                    wedlock: [
                        {required: true, message: '请选择婚姻状态', trigger: 'change'}
                    ],
                    nationId: [
                        {required: true, type: 'number', message: '请选择民族', trigger: 'change'}
                    ],
                    birthday: [
                        {required: true, type: 'date', message: '请选择出生日期', trigger: 'change'}
                    ],
                    phone: [
                        {required: true, message: '联系方式不能为空', trigger: 'blur'}
                    ],
                    titopDegree: [
                        {required: true, message: '请选择学历', trigger: 'change'}
                    ],
                    school: [
                        {required: true, message: '毕业院校不能为空', trigger: 'blur'}
                    ],
                    specialty: [
                        {required: true, message: '专业不能为空', trigger: 'blur'}
                    ],
                },
                passwordRules: {
                    oldpassword: [
                        { required: true, message: '旧密码不能为空', trigger: 'blur'}
                    ],
                    newpassword: [
                        {required: true, message: '密码不能为空', trigger: 'blur' },
                        {pattern: /^[0-9a-zA-Z]+$/, message: '密码不能包含中文或特殊字符', trigger: 'blur'},
                    ],
                    strPassword: [
                        { required: true, validator: this.strPassword, trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            onRpPageSizeChange(index){
                this.rpLimit = index;
            },
            rpPageChange(index){
                this.rpPage = index;
            },
            departAssPageChange(index){
                this.departAssPage = index;
            },
            getDropDownList(){
                this.getRequest("/employee/init").then(resp=> {
                    this.dropDownList = resp.data.data;
                })
            },
            initdata(){
                if(this.user.eid != null){
                    this.getPerEmp();
                    this.getEmpRp();
                    this.getDepartAss();
                }
            },
            getPerEmp(){
                this.getRequest("/employee/myfile").then(resp=>{
                    this.perEmpList = resp.data.data;
                    this.birthday = moment(this.perEmpList.birthday).format('YYYY-MM-DD');
                })
            },
            getEmpRp(){
                this.getRequest("/rp/empRp/" + this.$store.state.user.eid,{
                    page: this.rpPage,
                    limit: this.rpLimit,
                }).then(resp=>{
                    this.empRpLists = resp.data.data.list;
                    this.rpTotal = resp.data.data.total;
                })
            },
            getDepartAss() {
                this.getRequest("/empAssessment/myAssessment",{
                    limit: this.departAssLimit,
                    page: this.departAssPage,
                }).then(resp => {
                    this.departAssLists = resp.data.data.list;
                    this.departAssTotal = resp.data.data.total;
                })
            },
            beforeUpdate(){
                this.updateModal = true;
                this.perEmpUpdateList.id = this.perEmpList.id;
                this.perEmpUpdateList.workId = '' + this.perEmpList.workId;
                this.perEmpUpdateList.name = this.perEmpList.name;
                this.perEmpUpdateList.sex = this.perEmpList.sex;
                this.perEmpUpdateList.nationId = this.perEmpList.nationId;
                this.perEmpUpdateList.nationName = this.perEmpList.nationName;
                this.perEmpUpdateList.birthday = this.perEmpList.birthday;
                this.perEmpUpdateList.politiclId = this.perEmpList.politiclId;
                this.perEmpUpdateList.politiclName = this.perEmpList.politiclName;
                this.perEmpUpdateList.wedlock = this.perEmpList.wedlock;
                this.perEmpUpdateList.nativePlace = this.perEmpList.nativePlace;
                this.perEmpUpdateList.idCard = this.perEmpList.idCard;
                this.perEmpUpdateList.email = this.perEmpList.email;
                this.perEmpUpdateList.phone = this.perEmpList.phone;
                this.perEmpUpdateList.address = this.perEmpList.address;
                this.perEmpUpdateList.departmentId = this.perEmpList.departmentId;
                this.perEmpUpdateList.departmentName = this.perEmpList.departmentName;
                this.perEmpUpdateList.positionId = this.perEmpList.positionId;
                this.perEmpUpdateList.positionName = this.perEmpList.positionName;
                this.perEmpUpdateList.jobLevelId = this.perEmpList.jobLevelId;
                this.perEmpUpdateList.jobLevelName = this.perEmpList.jobLevelName;
                this.perEmpUpdateList.titopDegree = this.perEmpList.titopDegree;
                this.perEmpUpdateList.specialty = this.perEmpList.specialty;
                this.perEmpUpdateList.school = this.perEmpList.school;
                this.perEmpUpdateList.beginDate = this.perEmpList.beginDate;
                this.perEmpUpdateList.workState = this.perEmpList.workState;
                this.perEmpUpdateList.conversionTime = this.perEmpList.conversionTime;
                this.perEmpUpdateList.quitTime = this.perEmpList.quitTime;
                this.perEmpUpdateList.beginContract = this.perEmpList.beginContract;
                this.perEmpUpdateList.endContract = this.perEmpList.endContract;
                this.perEmpUpdateList.baseSalary = '' + this.perEmpList.baseSalary;
                this.perEmpUpdateList.picture = this.perEmpList.picture;
            },
            update(name) {
                this.$refs[name].validate((valid) =>{
                    if (valid) {
                        console.log(this.perEmpUpdateList);

                        this.putRequest("/employee/updateEmp",{
                            id: this.perEmpUpdateList.id,
                            name: this.perEmpUpdateList.name,
                            nationId: this.perEmpUpdateList.nationId,
                            sex: this.perEmpUpdateList.sex,
                            wedlock: this.perEmpUpdateList.wedlock,
                            idCard: this.perEmpUpdateList.idCard,
                            birthday: this.perEmpUpdateList.birthday,
                            politiclId: this.perEmpUpdateList.politiclId,
                            phone: this.perEmpUpdateList.phone,
                            nativePlace: this.perEmpUpdateList.nativePlace,
                            email: this.perEmpUpdateList.email,
                            address: this.perEmpUpdateList.address,
                            titopDegree: this.perEmpUpdateList.titopDegree,
                            school: this.perEmpUpdateList.school,
                            specialty: this.perEmpUpdateList.specialty,
                            picture: this.perEmpUpdateList.picture
                            // positionId: this.perEmpUpdateList.positionId,
                            // departmentId: this.perEmpUpdateList.departmentId,
                            // jobLevelId: this.perEmpUpdateList.jobLevelId,
                            // beginDate: this.perEmpUpdateList.beginDate,
                            // workState: this.perEmpUpdateList.workState,
                            // conversionTime: this.perEmpUpdateList.conversionTime,
                            // quitTime: this.perEmpUpdateList.quitTime,
                            // beginContract: this.perEmpUpdateList.beginContract,
                            // endContract: this.perEmpUpdateList.endContract,
                            // baseSalary: this.perEmpUpdateList.baseSalary,
                            // workId: this.perEmpUpdateList.workId,
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.updateModal = false;
                                this.getPerEmp();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    } else {
                        this.$Message.error('必填项不能为空');
                    }
                })
            },
            resetPsw(name){
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/user/password", {
                            id: this.$store.state.user.id,
                            oldPassword: this.password.oldpassword,
                            password: this.password.newpassword,
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                this.openResetPsw = false;
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            strPassword(rule, value, callback){
                if(value == null || value == ''){
                    return callback(new Error("不能为空"));
                }
                if(value != this.password.newpassword){
                    return callback(new Error("两次密码不一致!"));
                }
                callback();
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['password'].resetFields();
                }
            },
            uploadSuccess(resp, file){
                this.perEmpUpdateList.picture = resp.data;
            }
        },
        mounted: function (){
            this.getDropDownList();
            this.initdata();
        },
        watch: {
            rpPage: "getEmpRp",
            departAssPage: "getDepartAss",
        },
    }
</script>

<style>
    p{
        float: left;
    }
    .departDown{
        height: 30px;
    }
</style>