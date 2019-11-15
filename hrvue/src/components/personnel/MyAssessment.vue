<template>
    <div>
        <Row>
            <Col span="22">
                <Row :gutter="6">
                    <Col span="2">
                        <Select v-model="souFormItem.result" placeholder="审批结果" clearable>
                            <Option value="优秀">优秀</Option>
                            <Option value="良好">良好</Option>
                            <Option value="及格">及格</Option>
                            <Option value="不及格">不及格</Option>
                        </Select>
                    </Col>
                    <Col span="3">
                        <Input v-model="souFormItem.name" clearable placeholder="请输入考核名称"/>
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getAssessment" >搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2">
                <Button type="primary" @click="addModal = true">提交考核</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Table ref="selection" :columns="columns" :data="data">
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)" v-if="row.check == '否'">编辑</Button>
                    <Button type="primary" style="margin-right: 5px" @click="beforeUpdate(index)" v-else disabled>编辑</Button>
                    <Button type="error" @click="remove(row.id)" v-if="row.check == '否'">删除</Button>
                    <Button type="error" @click="remove(row.id)" v-else disabled>删除</Button>
                </template>
            </Table>
            <Row type="flex" justify="center"  :style="{margin: '10px 0 0 0'}">
                <Col><Page :total="rpTotal" :page-size="rpPageSize" show-elevator show-total @on-change="rpPageChange" @on-page-size-change="onRpPageSizeChange"/></Col>
            </Row>
        </Row>
        <Modal
            v-model="addModal"
            title="提交考核"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="myAssessmentList" :rules="rules" :label-width="80" ref="myAssessmentList">
                        <FormItem label="考核项目:" prop="name">
                            <Select v-model="myAssessmentList.name" placeholder="请选择考核项目" clearable>
                                <Option value="1">2019第一季度考核</Option>
                                <Option value="2">2019第二季度考核</Option>
                                <Option value="3">2019第三季度考核</Option>
                                <Option value="4">2019第四季度考核</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="附件:" prop="data">
                            <Upload
                                :before-upload="handleUpload"
                                action="//jsonplaceholder.typicode.com/posts/">
                                <Button icon="ios-cloud-upload-outline">上传文件</Button>
                            </Upload>
                            <div v-if="myAssessmentList.data !== null">已选择的文件: {{ myAssessmentList.data.name }}</div>
                        </FormItem>
                        <FormItem label="说明:" prop="remark">
                            <Input v-model="myAssessmentList.remark" type="textarea" placeholder="请输入考核说明"></Input>
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
        data() {
            return {
                addModal: false,
                updateModal: false,
                page:1,
                total: 100,
                limit: 10,
                beginDate: moment().locale('zh-cn').format('YYYY-MM-DD HH:mm:ss'),
                myAssessmentList:{
                    aid: '',
                    data: '',
                    remark: '',
                },
                myAssessmentLists:[],
                rules: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    remark: [
                        {type: 'string', max: 50, message: '备注长度不能超过50个字符'}
                    ]
                },
                souFormItem: {
                    result: '',
                    name: ''
                },
                columns: [
                    {
                        title: '考核名称',
                        key: 'name'
                    },
                    {
                        title: '说明',
                        key: 'remark'
                    },
                    {
                        title: '考核结果',
                        key: 'result'
                    },
                    {
                        title: '是否查阅',
                        key: 'check'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 280,
                        align: 'center'
                    }
                ],
                data: [
                    {
                        name: '第一季度考核',
                        remark: '没啥好说明的',
                        result: '优秀',
                        check: '是',
                    },
                    {
                        name: '第二季度考核',
                        remark: '没啥好说明的',
                        result: '',
                        check: '否',
                    }
                ]
            }
        },
        methods: {
            handleUpload (file) {
                this.myAssessmentList.data = file;
                return false;
            },
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
            getAssessment() {
                this.getRequest("/assessment/allAssesment",{
                    page: this.page,
                    limit: this.limit,
                    state: this.souFormItem.state,
                    name: this.souFormItem.name,
                }).then(resp=>{
                    this.assessments = resp.data.data.list;
                    this.total = resp.data.data.total;
                })
            },
            startASS(id,index){
                this.putRequest("/assessment/updateAssesmentState",{
                    id: id,
                    name: this.assessments[index].name,
                    state: "已开始",
                    beginDate: this.beginDate,
                }).then(resp => {
                    if (resp.data.code != 400) {
                        this.$Message.success(resp.data.data);
                        this.getAssessment();
                    } else {
                        this.$Message.error(resp.data.message);
                    }
                })
            },
            overAss(id,index){
                this.putRequest("/assessment/updateAssesmentState",{
                    id: id,
                    name: this.assessments[index].name,
                    state: "已结束",
                    endDate: this.endDate,
                }).then(resp => {
                    if (resp.data.code != 400) {
                        this.$Message.success(resp.data.data);
                        this.getAssessment();
                    } else {
                        this.$Message.error(resp.data.message);
                    }
                })
            },
            remove(id) {
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>你确定要删除该考核吗?</p>',
                    onOk: () => {
                        var _this = this;
                        this.deleteRequest("/assessment/deleteAssesment/" + id).then(resp=> {
                            this.$Message.success(resp.data.data);
                            _this.getAssessment();
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
            this.getAssessment();
        },
        watch: {
            page: "getAssessment",
            limit: "getAssessment",
        },
    }
</script>