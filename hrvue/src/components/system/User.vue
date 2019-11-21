<template>
    <div>
        <Row>
            <Col span="4">
                <Input v-model="keyword" placeholder="输入用户名进行查询" @on-change="check" clearable/>
            </Col>
            <Col span="2">
                <Button type="primary" icon="ios-search" @click="getUserList">查询</Button>
            </Col>
            <Col span="2" offset="16">
                <Button type="primary" icon="ios-add" @click="openAddNew=true">添加新用户</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="24">
                <Table  border  :columns="columns1" :data="users" :loading="loading">
                    <template slot-scope="{ row, index }" slot="role">
                        <Tag v-for="item in row.roles" :key="item.id">{{item.name}}</Tag>
                    </template>
                    <template slot-scope="{ row, index }" slot="state">
                        <div v-if="row.username == 'admin' ? true : false">
                            <i-switch size="large" v-model="row.enabled" @on-change="changeState(row.id,row.enabled)" disabled>
                                <span slot="open">启用</span>
                                <span slot="close">禁用</span>
                            </i-switch>
                        </div>
                        <div v-else>
                            <i-switch size="large" v-model="row.enabled" @on-change="changeState(row.id,row.enabled)">
                                <span slot="open">启用</span>
                                <span slot="close">禁用</span>
                            </i-switch>
                        </div>
                    </template>
                    <template slot-scope="{ row, index }" slot="action">
                        <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)" :disabled="row.id === 1 ? true : false">编辑</Button>
                        <Button type="warning" style="margin-right: 5px" @click="beforeResetPsw(index)" :disabled="row.id === 1 ? true : false">重置密码</Button>
                        <Button type="error" style="margin-right: 5px" @click="deleteUser(row.id)" :disabled="row.id === 1 ? true : false">删除</Button>
                    </template>
                </Table>
            </Col>
        </Row>
        <br>
        <Row>
            <Col>
                <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
            </Col>
        </Row>
        <Modal
            v-model="openAddNew"
            title="添加新用户"
            @on-visible-change="cancel">
            <Row>
                <Col span="21">
                    <Form :model="user" :rules="newUserRules" :label-width="80" ref="newUser">
                        <FormItem label="当前头像:" prop="userface">
                            <img src="../../assets/avatar.png" :style="{width:'128px',height:'128px',borderRadius: '50%'}" v-if="user.userface=='' || null ? true : false">
                            <img :src="user.userface" :style="{width:'128px',height:'128px',borderRadius: '50%'}" v-else>
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
                                action="http://localhost:8080/hrserver/system/user/userface"
                                style="width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传头像</Button>
                            </Upload>
                        </FormItem>
                        <FormItem label="用户名:" prop="username">
                            <Input v-model="user.username" placeholder="用户名..."></Input>
                        </FormItem>
                        <FormItem label="密码:" prop="password">
                            <Input type="password" v-model="user.password" placeholder="密码..."></Input>
                        </FormItem>
                        <FormItem label="个人姓名:" prop="name">
                            <Input v-model="user.name" placeholder="个人姓名..."></Input>
                        </FormItem>
                        <FormItem label="手机号:" prop="phone">
                            <Input v-model="user.phone" placeholder="手机号..."></Input>
                        </FormItem>
                        <FormItem label="联系地址:" prop="address">
                            <Input v-model="user.address" type="textarea" placeholder="地址..."></Input>
                        </FormItem>
                        <FormItem label="用户角色:" prop="roles">
                            <Select v-model="user.roles" multiple @on-change="selectchange">
                                <Option v-for="item in rolelist" :value="item.id" :key="item.id" :disabled="item.id == 2 ? true : false">{{ item.name }}</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="user.remark" type="textarea" placeholder="备注..."></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openAddNew=false">取消</Button>
                <Button type="primary" @click="addNewUser('newUser')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openUpdate"
            title="编辑"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="user" :rules="newUserRules" :label-width="80" ref="user">
                        <FormItem label="当前头像:" prop="userface">
                            <img src="../../assets/avatar.png" :style="{width:'128px',height:'128px',borderRadius: '50%'}" v-if="user.userface=='' || user.userface==null ? true : false">
                            <img :src="user.userface" :style="{width:'128px',height:'128px',borderRadius: '50%'}" v-else>
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
                                action="http://localhost:8080/hrserver/system/user/userface"
                                style="width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传头像</Button>
                            </Upload>
                        </FormItem>
                        <FormItem label="用户名:" prop="username">
                            <Input v-model="user.username" disabled placeholder="用户名..."></Input>
                        </FormItem>
                        <FormItem label="个人姓名:" prop="name">
                            <Input v-model="user.name" placeholder="个人姓名..."></Input>
                        </FormItem>
                        <FormItem label="手机号:" prop="phone">
                            <Input v-model="user.phone" placeholder="手机号..."></Input>
                        </FormItem>
                        <FormItem label="联系地址:" prop="address">
                            <Input v-model="user.address" type="textarea" placeholder="联系地址..."></Input>
                        </FormItem>
                        <FormItem label="用户角色:" prop="roles">
                            <Select v-model="user.roles" multiple @on-change="selectchange">
                                <Option v-for="item in rolelist" :value="item.id" :key="item.id" :disabled="item.id == 2 ? true : false">{{ item.name }}</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="user.remark" type="textarea" placeholder="备注..."></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openUpdate=false">取消</Button>
                <Button type="primary" @click="updateUser('user')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openResetPsw"
            title="重置密码"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="user" :rules="newUserRules" :label-width="80" ref="password">
                        <FormItem label="用户名:" prop="username">
                            <span>{{user.username}}</span>
                        </FormItem>
                        <FormItem label="新密码:" prop="password">
                            <Input v-model="user.password" type="password" placeholder="新密码"></Input>
                        </FormItem>
                        <FormItem label="重复密码:" prop="strPassword">
                            <Input v-model="user.strPassword" type="password" placeholder="重复密码"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="openResetPsw=false">取消</Button>
                <Button type="primary" @click="resetPsw('password')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
    </div>
</template> 
<script>
    import {isNotNullORBlank} from '../../utils/utils'
    export default{
        data(){
            return {
                headers: {
                    'token': this.$store.state.token,
                    'sessionId': this.$store.state.sessionId,
                },
                columns1: [
                    {
                        title: '用户名(登录账号)',
                        key: 'username'
                    },
                    {
                        title: '个人姓名',
                        key: 'name'
                    },
                    {
                        title: '手机号码',
                        key: 'phone'
                    },
                    {
                        title: '联系地址',
                        key: 'address'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '用户角色',
                        slot: 'role',
                    },
                    {
                        title: '状态',
                        width: '100',
                        slot: 'state',
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: '250',
                        align: 'center'
                    }
                ],
                users: [
                ],
                keyword: '',
                page:1,
                total: 100,
                limit: 10,
                loading: false,
                spinShow: false,
                openAddNew : false,
                openUpdate: false,
                openResetPsw: false,
                newUserRules: {
                    username: [
                        {required: true, message: '用户名不能为空', trigger: 'blur' },
                        {pattern: /^[0-9a-zA-Z]+$/, message: '用户名不能包含中文或特殊字符', trigger: 'blur'},
                    ],
                    phone: [
                        {required: true, message: '手机号不能为空', trigger: 'blur' },
                        {pattern: /^1(3|4|5|7|8)\d{9}$/, message: '手机号格式不正确', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur' },
                        {pattern: /^[0-9a-zA-Z]+$/, message: '密码不能包含中文或特殊字符', trigger: 'blur'},
                    ],
                    address: [
                        { type: 'string', max: 50, message: '地址长度不能超过50个字符'}
                    ],
                    name: [
                        {required: true, message: '个人姓名不能为空', trigger: 'blur' }
                    ],
                    remark: [
                        { type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ],
                    strPassword: [
                        { required: true, validator: this.validExit, trigger: 'blur'}
                    ],
                },
                user: {
                    id: 0,
                    name: '',
                    username: '',
                    password: '',
                    strPassword: '',
                    phone: '',
                    address: '',
                    userface: '',
                    remark: '',
                    roles: [2]
                },
                rolelist: [],
            }
        },
        mounted: function () {
            this.getUserList();
            this.getRoleList();
        },
        watch: {
            page: "getUserList",
            limit: "getUserList",
        },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            check(){
                if(!isNotNullORBlank(this.keyword)){
                    this.getUserList();
                }
            },
            getUserList(){
                this.loading = true;
                this.getRequest("/system/user/userlist",{
                    page: this.page,
                    limit: this.limit,
                    name: this.keyword,
                }).then(resp=> {
                    this.loading = false;
                    this.users = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            getRoleList(){
                this.loading = true;
                this.getRequest("/system/role/roles",{
                    page: 0,
                    limit: 0,
                }).then(resp=> {
                    this.loading = false;
                    this.rolelist = resp.data.data.list;
                })
            },
            addNewUser(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.user.name)){
                        this.$Message.error("个人姓名存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.postRequest("/system/user/addUser", {
                            username: this.user.username,
                            password: this.user.password,
                            name: this.user.name,
                            phone: this.user.phone,
                            address: this.user.address,
                            remark: this.user.remark,
                            userface: this.user.userface,
                            rolesKey: this.user.roles,
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getUserList();
                                this.$refs[name].resetFields();
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                this.openAddNew = false;
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            changeState(id,enabled){
                this.putRequest("/system/user/enabled", {
                    id: id,
                    enabled: enabled,
                }).then(resp=> {
                    if (resp.data.error == false && resp.data.code == 200) {
                        this.$Message.success("更新状态成功");
                    } else {
                        this.$Message.error(resp.data.message);
                    }
                })
            },
            beforeUpdate(index){
                this.openUpdate = true;
                this.user.id = this.users[index].id;
                this.user.name = this.users[index].name;
                this.user.username = this.users[index].username;
                this.user.phone = ''+this.users[index].phone;
                this.user.address = this.users[index].address;
                this.user.userface = this.users[index].userface;
                this.user.remark = this.users[index].remark;
                var roles = [];
                this.users[index].roles.forEach(element => {
                    roles.push(element.id)
                });
                this.user.roles = roles;
            },
            updateUser(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.user.name)){
                        this.$Message.error("个人姓名存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/user/updateUser", {
                            id: this.user.id,
                            name: this.user.name,
                            phone: this.user.phone,
                            address: this.user.address,
                            remark: this.user.remark,
                            userface: this.user.userface,
                            rolesKey: this.user.roles,
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getUserList();
                                this.$refs[name].resetFields();
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                this.openUpdate = false;
                            } else {
                                this.$Message.error(resp.data.message);
                                this.spinShow = false;
                            }
                        })
                    }
                })
            },
            beforeResetPsw(index){
                this.openResetPsw = true;
                this.user.id = this.users[index].id;
                this.user.username = this.users[index].username;
            },
            resetPsw(name){
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/user/resetpsw", {
                            id: this.user.id,
                            password: this.user.password,
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
            cancel(flag){
                if(flag == false){
                    this.$refs['newUser'].resetFields();
                    this.$refs['user'].resetFields();
                    this.$refs['password'].resetFields();
                }
            },
            selectchange(value){
                if (value.indexOf(2) == -1){
                    this.user.roles.push(2);
                    this.$Message.error("员工为必选基础角色");
                }
            },
            deleteUser(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>账号删除后将无法恢复!!!!!!</p><p>你确定要删除该账号吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/system/user/user/" + id).then(resp=> {
                            if(resp.data.code != 400){
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                _this.getUserList();
                            }else{
                                this.$Message.error(resp.data.message);
                            }
                        })
                    },
                });
            },
            validExit(rule, value, callback){
                if(value == null || value == ''){
                    return callback(new Error("不能为空"));
                }
                if(value != this.user.password){
                    return callback(new Error("两次密码不一致!"));
                }
                callback();
            },
            uploadSuccess(resp, file){
                this.user.userface = resp.data;
            }
        }
    }
</script>
<style>

</style>
