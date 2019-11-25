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
                <Button type="primary" @click="openaddAssessment">添加考核</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="columns" :data="assessments">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="startASS(row.id,index)" v-if="row.state == '未开始'">开始考核</Button>
                    <Button type="primary" style="margin-right: 5px" @click="overAss(row.id,index)" v-else-if="row.state == '已开始'">结束考核</Button>
                    <Button type="primary" style="margin-right: 5px" v-else disabled>考核结束</Button>
                    <Button type="primary" style="margin-right: 5px" @click="examine(index)">审核</Button>
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
        <Modal
            v-model="examineModal"
            title="审核列表"
            width=60%
            @on-visible-change="cancel">
            
            <Row :gutter="6">
                <Col span="5">
                    <Input v-model="souExamineName" clearable placeholder="请输入考核名称"/>
                </Col>
                <Col span="1">
                    <Button icon="ios-search" @click="examine" >搜索</Button>
                </Col>
            </Row>
            <br>
            <Table border ref="selection" :columns="examineColumns" :data="examineLists">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="beforeExamine(index)" v-if="row.commit == '有提交'">审核</Button>
                    <Button type="primary" style="margin-right: 5px" v-else disabled>未提交</Button>
                </template>
            </Table>
            <br>
            <Row type="flex" justify="center">
                <Page :total="examineTotal" :page-size="examinePageSize" show-elevator show-total @on-change="examinePageChange"/>
            </Row>
            <div slot="footer">
                <Button @click="examineModal = flase">返回</Button>
            </div>
        </Modal>
        <Modal
            v-model="examineingModal"
            title="审核考核"
            @on-visible-change="cancel">
            
            <Form :label-width="100" ref="beforeExamineAss" :model="beforeExamineAss" :rules="examineRules">
                 <Row>
                    <FormItem label="考核附件：">
                        <a :href="beforeExamineAss.data"  target="_blank" download>{{beforeExamineAss.data|formatData}}</a>
                    </FormItem>
                </Row>
                <Row>
                    <FormItem label="提交说明：">
                        {{beforeExamineAss.remark}}
                    </FormItem>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="考核结果：" prop="result">
                            <Select v-model="beforeExamineAss.result" clearable>
                                <Option value="未通过">未通过</Option>
                                <Option value="通过">已通过</Option>
                                <Option value="打回修改">打回修改</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button type="primary" @click="updateExamineAss('beforeExamineAss')">提交审核</Button>
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
                examineModal: false,
                examineingModal: false,
                page:1,
                total: 100,
                limit: 10,
                examinePage: 1,
                examineTotal: 100,
                examineLimit: 5,
                examinePageSize: 5,
                aid: '',
                createDate: moment().locale('zh-cn').format('YYYY-MM-DD HH:mm:ss'),
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
                examineLists: [],
                souExamineName: '',
                examineAss:{},
                beforeExamineAss:{
                    id: '',
                    data: '',
                    remark: '',
                    result: '',
                },
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
                examineRules: {
                    result: [
                        {required: true, message: '考核结果不能为空', trigger: 'change'}
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
                        width: 300,
                        align: 'center'
                    }
                ],
                examineColumns: [
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '部门',
                        key: 'departmentName'
                    },
                    {
                        title: '职位',
                        key: 'positionName'
                    },
                    {
                        title: '是否提交',
                        key: 'commit'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 110,
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
            onExaminePageSizeChange(index){
                this.examineLimit = index;
            },
            examinePageChange(index){
                this.examinePage = index;
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
                            did: this.newAssessment.departmentId,
                            createDate: this.createDate,
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
            examine(index) {
                this.getRequest("/reviewAssessment",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.examineModal = true;
                        if(this.aid == null || this.aid == ''){
                            this.aid = this.assessments[index].id;
                        }
                        this.getRequest("/assessment/"+ this.aid +"/empAssessment",{
                            page: this.examinePage,
                            limit: this.examineLimit,
                            name: this.souExamineName,
                        }).then(resp=>{
                            this.examineLists = resp.data.data.list;
                            this.examineTotal = resp.data.data.total;
                        })
                    }
                })
            },
            beforeExamine(index){
                this.examineingModal = true;
                this.getRequest("/assessment/empAssessment/" + this.examineLists[index].eaid).then(resp=>{
                    this.examineAss = resp.data.data;
                    this.beforeExamineAss.id = this.examineAss.id;
                    this.beforeExamineAss.data = this.examineAss.data;
                    this.beforeExamineAss.remark = this.examineAss.remark;
                    this.beforeExamineAss.result = this.examineAss.result;
                })
            },
            updateExamineAss(name){
                this.$refs[name].validate((valid) => {
                    if(valid){
                        this.postRequest("/empAssessment/updateEmployeeAssessment",{
                            id: this.beforeExamineAss.id,
                            result: this.beforeExamineAss.result,
                            check: true,
                        }).then(resp => {
                            if(resp.data.error == false && resp.data.code == 200){
                                this.getAssessment();
                                this.$Message.success(resp.data.data);
                                this.examineingModal = false;

                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            startASS(id,index){
                this.getRequest("/changeAssState",null).then(resp=>{
                    if(resp.data.code != 403){
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
                    }
                })
            },
            overAss(id,index){
                this.getRequest("/changeAssState",null).then(resp=>{
                    if(resp.data.code != 403){
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
                    }
                })
            },
            remove(id) {
                this.getRequest("/deleteAssessment",null).then(resp=>{
                    if(resp.data.code != 403){
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
                    }
                })
            },
            beforeUpdate(index) {
                this.getRequest("/modifyAssessment",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.updateModal = true;
                        this.assessment.id = this.assessments[index].id;
                        this.assessment.name = this.assessments[index].name;
                        this.assessment.remarks = this.assessments[index].remarks;
                    }
                })
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
                    this.$refs['beforeExamineAss'].resetFields();
                }
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            openaddAssessment(){
                this.getRequest("/addAssessment",null).then(resp=>{
                    if(resp.data.code != 403){
                        this.addModal = true;
                    }
                })
            }
        },
        mounted: function (){
            this.getDropDownList();
            this.getAssessment();
        },
        watch: {
            page: "getAssessment",
            examinePage: "examine",
            limit: "getAssessment",
        },
        filters: {
            formatData:function(url){
                var find = function(str,cha,num){
                    var x=str.indexOf(cha);
                    for(var i=0;i<num;i++){
                        x=str.indexOf(cha,x+1);
                    }
                    return x;
                }
                if(url != '' && url != null){
                    var index = find(url,'/',4);
                    var varDataNmae = url.substring(index + 1);
                    return varDataNmae;
                } else {
                    return '';
                }
            }
        }
    }
</script>