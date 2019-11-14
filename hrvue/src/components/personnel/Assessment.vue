<template>
    <div>
        <Row>
            <Col span="22">
                <Row :gutter="6">
                    <Col span="3">
                        <treeselect class="departDown" v-model="souFormItem.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>                            
                    </Col>
                    <Col span="2">
                        <Select v-model="souFormItem.state" placeholder="考核状态" clearable>
                            <Option value="未开始">未开始</Option>
                            <Option value="已开始">已开始</Option>
                            <Option value="已结束">已结束</Option>
                        </Select>
                    </Col>
                    <Col span="5">
                        <Input v-model="souFormItem.name" clearable placeholder="请输入考核名称"/>
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getAssessment" >搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2">
                <Button type="primary" @click="addModal = true">添加考核</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="assessments">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="startASS(row.id,index)" v-if="row.state == '未开始'">开始考核</Button>
                    <Button type="primary" style="margin-right: 5px" @click="overAss(row.id,index)" v-else-if="row.state == '已开始'">结束考核</Button>
                    <Button type="primary" style="margin-right: 5px" v-else disabled>考核结束</Button>
                    <Button type="primary" style="margin-right: 5px"  @click="beforeUpdate(index)">编辑</Button>
                    <Button type="error" @click="remove(row.id)">删除</Button>
                </template>
            </Table>
        </Row>
        <Row :style="{margin: '20px 0 0 0'}">
            <Page :total="total" show-sizer show-elevator show-total @on-change="pageChange" @on-page-size-change="onPageSizeChange"/>
        </Row>
        <Modal
            v-model="addModal"
            title="添加考核项目"
            width=30%
            @on-visible-change="cancel">
            
                <Form ref="newAssessment" :model="newAssessment" :rules="rules" :label-width="100">
                    <Row>
                        <FormItem label="考核名称"  prop="name">
                            <Input v-model="newAssessment.name" placeholder="请输入"></Input>
                        </FormItem>
                    </Row>
                    <Row>
                        <FormItem label="考核部门"  prop="departmentId">
                            <treeselect class="departDown" v-model="newAssessment.departmentId" :options="dropDownList.departmentList" :default-expand-level="1" placeholder="请选择部门"/>
                        </FormItem>
                    </Row>
                    <Row>
                        <FormItem label="备注" prop="remark">
                            <Input v-model="newAssessment.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Row>
                </Form>
                <div slot="footer">
                    <Button @click="handleReset('newAssessment')">重置</Button>
                    <Button type="primary" @click="addAssessment('newAssessment')">保存</Button>
                </div>
        </Modal>
        <Modal
            v-model="updateModal"
            title="修改考核项目"
            width=30%
            @on-visible-change="cancel">
            
                <Form ref="assessment" :model="assessment" :rules="rules" :label-width="100">
                    <Row>
                        <FormItem label="考核名称"  prop="name">
                            <Input v-model="assessment.name" placeholder="请输入"></Input>
                        </FormItem>
                    </Row>
                    <Row>
                        <FormItem label="备注" prop="remark">
                            <Input v-model="assessment.remark" type="textarea" placeholder="备注"></Input>
                        </FormItem>
                    </Row>
                </Form>
                <div slot="footer">
                    <Button @click="handleReset('assessment')">重置</Button>
                    <Button type="primary" @click="update('assessment')">保存</Button>
                </div>
        </Modal>
    </div>
</template>
<script>
    import Treeselect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'
    import moment from "moment"
    export default {
        components: { Treeselect },
        data() {
            return {
                addModal: false,
                updateModal: false,
                page:1,
                total: 100,
                limit: 10,
                beginDate: moment().locale('zh-cn').format('YYYY-MM-DD HH:mm:ss'),
                endDate: moment().locale('zh-cn').format('YYYY-MM-DD HH:mm:ss'),
                newAssessment: {
                    name: '',
                    departmentId: null,
                    state: '未开始',
                    remarks: '',
                },
                assessment:{
                    id: '',
                    name: '',
                    state: '',
                    createDate: '',
                    beginDate: '',
                    endDate: '',
                    remarks: '',
                },
                dropDownList: [],
                assessments:[],
                rules: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    departmentId :[
                        {required: true, type: 'number', message: '考核部门不能为空', trigger: 'change'}
                    ],
                    remark: [
                        {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
                },
                souFormItem: {
                    departmentId: null,
                    state: '',
                    name: ''
                },
                columns: [
                    {
                        title: '考核编号',
                        key: 'id'
                    },
                    {
                        title: '考核名称',
                        key: 'name'
                    },
                    {
                        title: '考核部门',
                        key: 'departmentName'
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
                        title: '备注',
                        key: 'remarks'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 280,
                        align: 'center'
                    }
                ],
            }
        },
        methods: {
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            addAssessment(name) {
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) => {
                    if (check.test(this.newAssessment.name)){
                        this.$Message.error("考核名称存在特殊字符");
                        return;
                    }
                    if(valid) {
                        this.postRequest("/assessment/addAssesment",{
                            name: this.newAssessment.name,
                            state: this.newAssessment.state,
                            remark: this.newAssessment.remark
                        }).then(resp=> {
                            if (resp.data.error == false && resp.data.code == 200) {
                                this.newAssessment.name = '';
                                this.newAssessment.remark = '';
                                this.$Message.success(resp.data.data);
                                this.addModal = false;
                                this.getAssessment();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            getDropDownList(){
                this.getRequest("/employee/init").then(resp=> {
                    this.dropDownList = resp.data.data;
                })
            },
            getAssessment() {
                this.getRequest("/assessment/allAssesment",{
                    page: this.page,
                    limit: this.limit,
                    did: this.souFormItem.departmentId,
                    state: this.souFormItem.state,
                    name: this.souFormItem.name,
                }).then(resp=>{
                    this.assessments = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            startASS(id,index){
                this.$Modal.confirm({
                    title: '开始考核',
                    content: '<p>你确定要开始该考核吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.putRequest("/assessment/updateAssesmentState",{
                            id: id,
                            name: this.assessments[index].name,
                            state: "已开始",
                            beginDate: this.beginDate,
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                _this.getAssessment();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    },
                });
            },
            overAss(id,index){
                this.$Modal.confirm({
                    title: '结束考核',
                    content: '<p>你确定要结束该考核吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.putRequest("/assessment/updateAssesmentState",{
                            id: id,
                            name: this.assessments[index].name,
                            state: "已结束",
                            endDate: this.endDate,
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                _this.getAssessment();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    },
                });
            },
            remove(id) {
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>你确定要删除该考核吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/assessment/deleteAssesment/" + id).then(resp=> {
                            if(resp.data.code != 400){
                                this.$Message.success(resp.data.data);
                                this.spinShow = false;
                                _this.getAssessment();
                            }else{
                                this.$Message.error(resp.data.message);
                            }
                        })
                    },
                })
            },
            beforeUpdate(index) {
                this.updateModal = true;
                this.assessment.id = this.assessments[index].id;
                this.assessment.name = this.assessments[index].name;
                this.assessment.remarks = this.assessments[index].remarks;
            },
            update(name){
                var check = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]/im;
                this.$refs[name].validate((valid) =>{
                    if (check.test(this.newAssessment.name)){
                        this.$Message.error("考核名称存在特殊字符");
                        return;
                    }
                    if (valid) {
                        this.putRequest("/assessment/updateAssesment",{
                            id: this.assessment.id,
                            name: this.assessment.name,
                            remarks: this.assessment.remarks
                        }).then(resp => {
                            if (resp.data.code != 400) {
                                this.$Message.success(resp.data.data);
                                this.updateModal = false;
                                this.getAssessment();

                                //初始化字段
                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    } else {
                        this.$Message.error('考核名称不能为空');
                    }
                })
            },
            cancel(flag){
                if(flag == false){
                    this.$refs['newAssessment'].resetFields();
                    this.$refs['assessment'].resetFields();
                }
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
        },
        mounted: function (){
            this.getDropDownList();
            this.getAssessment();
        },
        watch: {
            page: "getAssessment",
            limit: "getAssessment",
        },
    }
</script>