<template>
    <div>
        <Row>
            <Col span="4">
                <Input v-model="keyword" placeholder="输入职位名进行查询" @on-change="check" clearable/>
            </Col>
            <Col span="2">
                <Button type="primary" icon="ios-search" @click="getPositionList">查询</Button>
            </Col>
            <Col span="2" offset="16">
                <Button type="primary" icon="ios-add" @click="openAddNew=true">添加新职位</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="24">
                <Table  border  :columns="columns1" :data="positionList" :loading="loading">
                    <template slot-scope="{ row, index }" slot="action">
                            <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)">编辑</Button>
                            <Button type="error" style="margin-right: 5px" @click="deletePosition(row.id)">删除</Button>
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
            title="添加新职位">
            <Row>
                <Col span="21">
                    <Form :model="newPosition" :rules="newPositionRules" :label-width="80" ref="newPosition">
                        <FormItem label="职位名称:" prop="name">
                            <Input v-model="newPosition.name" placeholder="请输入职位名称"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="newPosition.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('newPosition')">取消</Button>
                <Button type="primary" @click="addNewPosition('newPosition')">保存</Button>
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
                    <Form :model="position" :rules="newPositionRules" :label-width="80" ref="position">
                        <FormItem label="角色名:" prop="name">
                            <Input v-model="position.name" placeholder="请输入角色名"></Input>
                        </FormItem>
                        <FormItem label="备注:" prop="remark">
                            <Input v-model="position.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="cancel('position')">取消</Button>
                <Button type="primary" @click="updatePosition('position')">保存</Button>
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
                        title: '职位名称',
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
                positionList: [],
                keyword: '',
                page:1,
                total: 100,
                limit: 10,
                loading: false,
                spinShow: false,
                openAddNew : false,
                openUpdate: false,
                newPosition: {
                    name: '',
                    remark: ''
                },
                newPositionRules: {
                    name: [
                        {required: true, message: '职位名称不能为空', trigger: 'blur' },
                        {max:10, message: '长度不能超过10位'},
                    ],
                    remark: [
                        {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
                },
                position: {
                    id: 0,
                    name: '',
                    remark: ''
                },
            }
        },
        mounted: function () {
            this.getPositionList();
        },
        watch: {
            page: "getPositionList",
            limit: "getPositionList",
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
                    this.getPositionList();
                }
            },
            getPositionList(){
                this.loading = true;
                this.getRequest("/system/basic/positionList",{
                    page: this.page,
                    limit: this.limit,
                    name: this.keyword,
                }).then(resp=> {
                    this.loading = false;
                    this.positionList = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            addNewPosition(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.newPosition.name)){
                        this.$Message.error("职位名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.postRequest("/system/basic/position", {
                            name: this.newPosition.name,
                            remark: this.newPosition.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getPositionList();
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
                this.position.id = this.positionList[index].id;
                this.position.name = this.positionList[index].name;
                this.position.createDate = this.positionList[index].createDate;
                this.position.remark = this.positionList[index].remark;
            },
            updatePosition(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.position.name)){
                        this.$Message.error("职位名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.spinShow = true;
                        this.putRequest("/system/basic/position", {
                            id: this.position.id,
                            name: this.position.name,
                            remark: this.position.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.getPositionList();
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
                var check = /\s/;
            },
            cancel(name){
                this.openAddNew = false;
                this.openUpdate = false;
                this.$refs[name].resetFields();
            },
            deletePosition(id){
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>删除后相关账号的职位将更改为"普通员工"</p><p>你确定要删除该职位吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/system/basic/position/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            _this.getPositionList();
                        })
                    },
                });
            },
        }
    }
</script>
<style>

</style>
