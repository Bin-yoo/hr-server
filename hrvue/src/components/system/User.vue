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
                        <div v-if="row.username == 'admin' ? true : false">
                            <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)" disabled>编辑</Button>
                            <Button type="error" style="margin-right: 5px" @click="deleteUser(row.id)" disabled>删除</Button>
                        </div>
                        <div v-else>
                            <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)">编辑</Button>
                            <Button type="error" style="margin-right: 5px" @click="deleteUser(row.id)">删除</Button>
                        </div>
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
            title="添加新用户">
            <Row>
                <Col span="21">
                    <Form :model="newUser" :rules="newUserRules" :label-width="80" ref="newUser">
                        <FormItem label="当前头像:" prop="userface">
                            <img src="https://wwc.alicdn.com/avatar/getAvatar.do?userId=822908364&width=80&height=80&type=sns" :style="{width:'128px',height:'128px',borderRadius: '50%'}">
                            <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                multiple
                                type="drag"
                                action="//jsonplaceholder.typicode.com/posts/"
                                style="width:128px;">
                                <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传头像</Button>
                            </Upload>
                        </FormItem>
                        <FormItem label="用户名:" prop="username">
                            <Input v-model="newUser.username"></Input>
                        </FormItem>
                        <FormItem label="用户姓名:" prop="name">
                            <Input v-model="newUser.name" placeholder="备注"></Input>
                        </FormItem>
                        <FormItem label="手机号:" prop="phone">
                            <Input v-model="newUser.phone" placeholder="请输入用户姓名"></Input>
                        </FormItem>
                        <FormItem label="联系地址:" prop="address">
                            <Input v-model="newUser.address" type="textarea" placeholder="地址..."></Input>
                        </FormItem>
                        <FormItem label="用户角色:" prop="roles">
                            <Select v-model="model10" multiple>
                                <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="newUser.remark" type="textarea" placeholder="备注..."></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel">取消</Button>
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
            footer-hide>
            <Tabs size="small">
                <TabPane label="修改用户信息">
                    <Row>
                        <Col span="22">
                            <Form :model="user" :rules="newUserRules" :label-width="80" ref="user">
                                <FormItem label="当前头像:" prop="userface">
                                    <img src="https://wwc.alicdn.com/avatar/getAvatar.do?userId=822908364&width=80&height=80&type=sns" :style="{width:'128px',height:'128px',borderRadius: '50%'}">
                                    <Upload
                                        ref="upload"
                                        :show-upload-list="false"
                                        :format="['jpg','jpeg','png']"
                                        :max-size="2048"
                                        multiple
                                        type="drag"
                                        action="//jsonplaceholder.typicode.com/posts/"
                                        style="width:128px;">
                                        <Button icon="ios-cloud-upload-outline" :style="{width:'120px',border:'none'}">上传头像</Button>
                                    </Upload>
                                </FormItem>
                                <FormItem label="用户名:" prop="username">
                                    <Input v-model="user.username" disabled></Input>
                                </FormItem>
                                <FormItem label="用户姓名:" prop="name">
                                    <Input v-model="user.name" placeholder="备注"></Input>
                                </FormItem>
                                <FormItem label="手机号:" prop="phone">
                                    <Input v-model="user.phone" placeholder="请输入用户姓名"></Input>
                                </FormItem>
                                <FormItem label="联系地址:" prop="address">
                                    <Input v-model="user.address" type="textarea" placeholder="备注"></Input>
                                </FormItem>
                                <FormItem label="用户角色:" prop="roles">
                                    <Select v-model="model10" multiple>
                                        <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                                    </Select>
                                </FormItem>
                                <FormItem label="备注:" prop="remark">
                                    <Input v-model="user.remark" type="textarea" placeholder="备注"></Input>
                                </FormItem>
                            </Form>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="18">
                            <Button type="primary" long @click="updateUser('user')">保存</Button>
                        </Col>
                    </Row>
                </TabPane>
                <TabPane label="重置密码">
                    <Row>
                        <Col span="22">
                            <Form :model="user" :rules="newUserRules" :label-width="80" ref="password">
                                <FormItem label="用户名:" prop="username">
                                    <span>{{user.username}}</span>
                                </FormItem>
                                <FormItem label="旧密码:" prop="phone">
                                    <Input v-model="user.oldpassword" placeholder="请输入用户姓名"></Input>
                                </FormItem>
                                <FormItem label="新密码:" prop="address">
                                    <Input v-model="user.password" type="textarea" placeholder="备注"></Input>
                                </FormItem>
                                <FormItem label="重复新密码:" prop="roles">
                                    <Input v-model="user.strPassword" type="textarea" placeholder="备注"></Input>
                                </FormItem>
                            </Form>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="18">
                            <Button type="primary" long @click="updatePassword('password')">确认修改</Button>
                        </Col>
                    </Row>
                </TabPane>
            </Tabs>
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
                date: new Date(),
                columns1: [
                    {
                        title: '用户名(登录账号)',
                        key: 'username'
                    },
                    {
                        title: '用户姓名',
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
                        width: '180',
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
                openUser: false,
                newUser: {
                    name: '',
                    username: '',
                    phone: '',
                    address: '',
                    userface: '',
                    remark: '',
                    rols: []
                },
                newUserRules: {
                    username: [
                        {required: true, message: '用户名不能为空', trigger: 'blur' },
                        {pattern: /^[0-9a-zA-Z]+$/, message: '用户名不能输入中文或特殊字符', trigger: 'blur'},
                    ],
                    phone: [
                        {required: true, message: '手机号不能为空', trigger: 'blur' },
                        {pattern: /^1(3|4|5|7|8)\d{9}$/, message: '手机号格式不正确', trigger: 'blur'},
                    ],
                    address: [
                        { type: 'string', max: 50, message: '地址长度不能超过50个字符'}
                    ],
                    name: [
                        {required: true, message: '用户姓名不能为空', trigger: 'blur' }
                    ],
                    remark: [
                        { type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
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
                    remark: ''
                },
                treeData: [],
                treeRid:'',
            }
        },
        mounted: function () {
            this.getUserList();
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
            addNewUser(name){
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success('Success!');
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
                // var check = /\s/;
                // if(!check.test(this.newUser.name) && isNotNullORBlank(this.newUser.name)){
                //     this.spinShow = true;
                //     this.postRequest("/system/user/addUser", {
                //         name: this.newUser.name,
                //         remark: this.newUser.remark
                //     }).then(resp=> {
                //         if (resp.data.error == false && resp.data.code == 200) {
                //             this.getUserList();
                //             this.newUser.name = '';
                //             this.newUser.remark = '';
                //             this.$Message.success(resp.data.data);
                //             this.spinShow = false;
                //             this.openAddNew = false;
                //         } else {
                //             this.$Message.error(resp.data.message);
                //             this.spinShow = false;
                //         }
                //     })
                // } else {
                //     this.$Message.error("用户姓名不能为空");
                // }
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
                this.user.phone = this.users[index].phone;
                this.user.address = this.users[index].address;
                this.user.userface = this.users[index].userface;
                this.user.remark = this.users[index].remark;
            },
            updateUser(name){
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success('Success!');
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
                var check = /\s/;
                if(!check.test(this.user.name) && isNotNullORBlank(this.user.name)){
                    this.spinShow = true;
                    this.putRequest("/system/user/updateUser", {
                        id: this.user.id,
                        name: this.user.name,
                        remark: this.user.remark
                    }).then(resp=> {
                        if (resp.data.error == false && resp.data.code == 200) {
                            this.getUserList();
                            this.user.id = 0;
                            this.user.name = '';
                            this.user.remark = '';
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            this.openUpdate = false;
                        } else {
                            this.$Message.error(resp.data.message);
                            this.spinShow = false;
                        }
                    })
                } else {
                    this.$Message.error("用户姓名不能为空");
                }
            },
            cancel(){
                this.openAddNew = false;
                this.openUpdate = false;
                this.openUser = false;
                this.newUser.name = '';
                this.newUser.remark = '';
            },
            updateCancel(flag){
                if(flag == false){
                    this.user.name = '';
                    this.user.remark = '';
                }
            },
            deleteUser(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>如果该账号为员工账号,您先需要把该员工的档案删除</p><p>你确定要删除该账号吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/system/user/user/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            _this.getUserList();
                        })
                    },
                });
            },
        }
    }
</script>
<style>

</style>
