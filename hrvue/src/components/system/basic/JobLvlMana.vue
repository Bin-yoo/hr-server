<template>
    <div>
        <Row>
            <Col span="4">
                <Input v-model="keyword" placeholder="输入职称名进行查询" @on-change="check" clearable/>
            </Col>
            <Col span="2">
                <Button type="primary" icon="ios-search" @click="getjoblvlList">查询</Button>
            </Col>
            <Col span="2" offset="16">
                <Button type="primary" icon="ios-add" @click="openAddNew=true">添加新职称</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="24">
                <Table  border  :columns="columns1" :data="joblvls" :loading="loading">
                    <template slot-scope="{ row, index }" slot="action">
                            <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)">编辑</Button>
                            <Button type="error" style="margin-right: 5px" @click="deleteJobLvl(row.id)">删除</Button>
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
            title="添加新职称">
            <Row>
                <Col span="21">
                    <Form :model="newJobLvl" :rules="newJobLvlRules" :label-width="80" ref="newJobLvl">
                        <FormItem label="职称名称:" prop="name">
                            <Input v-model="newJobLvl.name" placeholder="请输入职称名称"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="newJobLvl.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('newJobLvl')">取消</Button>
                <Button type="primary" @click="addNewJobLvl('newJobLvl')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
        <Modal
            v-model="openUpdate"
            title="编辑角色信息">
            <Row>
                <Col span="21">
                    <Form :model="joblvl" :rules="newJobLvlRules" :label-width="80" ref="joblvl">
                        <FormItem label="角色名:" prop="name">
                            <Input v-model="joblvl.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="joblvl.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('joblvl')">取消</Button>
                <Button type="primary" @click="updateJobLvl('joblvl')">保存</Button>
            </div>
            <Spin fix v-if="spinShow">
                <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                <div>Loading</div>
            </Spin>
        </Modal>
    </div>
</template> 
<script>
    import {isNotNullORBlank} from '../../../utils/utils'
    export default{
        data(){
            return {
                date: new Date(),
                columns1: [
                    {
                        title: '职称名称',
                        key: 'name'
                    },
                    {
                        title: '创建时间',
                        key: 'createDate'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 180,
                        align: 'center'
                    }
                ],
                joblvls: [],
                keyword: '',
                page:1,
                total: 100,
                limit: 10,
                loading: false,
                spinShow: false,
                openAddNew : false,
                openUpdate: false,
                newJobLvl: {
                    name: '',
                    remark: ''
                },
                newJobLvlRules: {
                    name: [
                        {required: true, message: '职称名称不能为空', trigger: 'blur' },
                        {max:10, message: '长度不能超过10位'},
                    ],
                    remark: [
                        {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
                },
                joblvl: {
                    id: 0,
                    name: '',
                    remark: ''
                },
            }
        },
        mounted: function () {
            this.getjoblvlList();
        },
        watch: {
            page: "getjoblvlList",
            limit: "getjoblvlList",
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
                    this.getjoblvlList();
                }
            },
            getjoblvlList(){
                this.loading = true;
                this.getRequest("/system/basic/jobLvlList",{
                    page: this.page,
                    limit: this.limit,
                    name: this.keyword,
                }).then(resp=> {
                    this.loading = false;
                    this.joblvls = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            addNewJobLvl(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.newJobLvl.name)){
                        this.$Message.error("职称名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.postRequest("/system/basic/jobLvl", {
                            name: this.newJobLvl.name,
                            remark: this.newJobLvl.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getjoblvlList();
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
            beforeUpdate(index){
                this.openUpdate = true;
                this.joblvl.id = this.joblvls[index].id;
                this.joblvl.name = this.joblvls[index].name;
                this.joblvl.createDate = this.joblvls[index].createDate;
                this.joblvl.remark = this.joblvls[index].remark;
            },
            updateJobLvl(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.joblvl.name)){
                        this.$Message.error("职称名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/basic/jobLvl", {
                            id: this.joblvl.id,
                            name: this.joblvl.name,
                            remark: this.joblvl.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getjoblvlList();
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
            cancel(name){
                this.openAddNew = false;
                this.openUpdate = false;
                this.$refs[name].resetFields();
            },
            deleteJobLvl(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>删除后相关账号的职称将更改为"普通员工"</p><p>你确定要删除该职称吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/system/basic/jobLvl/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            _this.getjoblvlList();
                        })
                    },
                });
            },
        }
    }
</script>
<style>

</style>
