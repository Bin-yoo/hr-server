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
                        <Button icon="ios-search" @click="getMyAssessment" >搜索</Button>
                    </Col>
                </Row>
            </Col>
            <Col span="2">
                <Button type="primary" @click="addAss">提交考核</Button>
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
                <!-- <Col><Page :total="rpTotal" :page-size="rpPageSize" show-elevator show-total @on-change="rpPageChange" @on-page-size-change="onRpPageSizeChange"/></Col> -->
            </Row>
        </Row>
        <Modal
            v-model="addModal"
            title="提交考核"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="myAssessmentList" :rules="rules" :label-width="100" ref="myAssessmentList">
                        <FormItem label="选择考核项目:" prop="name">
                            <Button @click="selectAss">选择</Button>
                        </FormItem>
                        <FormItem label="考核项目:" prop="name">
                            {{myAssessmentList.name}}
                        </FormItem>
                        <FormItem label="附件:" prop="data">
                            <Upload
                                ref="upload"
                                :on-success="uploadSuccess"
                                name="picture"
                                action="http://111.230.141.100:8080/hrserver/empAssessment/data">
                                <Button icon="ios-cloud-upload-outline">上传文件</Button>
                            </Upload>
                            <div v-if="file !== null">已选择的文件: {{ file.name }}</div>
                        </FormItem>
                        <FormItem label="说明:" prop="remark">
                            <Input v-model="myAssessmentList.remark" type="textarea" placeholder="请输入考核说明"></Input>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
            <div slot="footer">
                <Button @click="handleReset('myAssessmentList')">重置</Button>
                <Button type="primary" @click="addMyAss('myAssessmentList')">保存</Button>
            </div>
        </Modal>
        <Modal
            v-model="selectAssModal"
            title="选择考核项目"
            width=70%>
            <Row :gutter="6">
                <Col span="3">
                    <Select v-model="souAssItem.state" placeholder="考核状态" clearable>
                        <Option value="未开始">未开始</Option>
                        <Option value="已开始">已开始</Option>
                        <Option value="已结束">已结束</Option>
                    </Select>
                </Col>
                <Col span="5">
                    <Input v-model="souAssItem.name" clearable placeholder="请输入考核名称"/>
                </Col>
                <Col span="2">
                    <Button icon="ios-search" @click="getDepartAss" >搜索</Button>
                </Col>
            </Row>
            <br>
            <Row>
                <Table border ref="selection" :columns="departAssColumns" :data="departAssLists">
                    <template slot-scope="{ row, index }" slot="action">
                        <Button type="primary" style="margin-right: 5px" @click="selected(index)" v-if="row.state == '已结束'" disabled>选择</Button>
                        <Button type="primary" style="margin-right: 5px" @click="selected(index)" v-else>选择</Button>
                    </template>
                </Table>
            </Row>
            <br>
            <Row type="flex" justify="center">
                <Page :total="departAssTotal" :page-size="departAssPageSize" show-elevator show-total @on-change="departAssPageChange"/>
            </Row>
            <div slot="footer">
                <Button @click="selectAssModal=false">返回</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import moment from "moment"
    export default {
        data() {
            return {
                file: '',
                addModal: false,
                updateModal: false,
                selectAssModal: false,
                departAssPage: 1,
                departAssTotal: 100,
                departAssLimit: 5,
                departAssPageSize: 5,
                page:1,
                total: 100,
                limit: 10,
                perEmpList: {},
                departAssLists: [],
                beginDate: moment().locale('zh-cn').format('YYYY-MM-DD HH:mm:ss'),
                myAssessmentList:{
                    aid: '',
                    name: '',
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
                souAssItem:{
                    state: '',
                    name: '',
                },
                souFormItem: {
                    result: '',
                    name: ''
                },
                departAssColumns: [
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
                        width: 100,
                        align: 'center'
                    }
                ],
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
                this.file = file;
                return false;
            },
            onPageSizeChange(index){
                this.limit = index;
            },
            pageChange(index){
                this.page = index;
            },
            departAssPageChange(index){
                this.departAssPage = index;
            },
            addAss(){
                this.addModal = true;
            },
            getPerEmp(){
                this.getRequest("/employee/myfile").then(resp=>{
                    this.perEmpList = resp.data.data;
                })
            },
            getDepartAss() {
                this.getRequest("/assessment/myAssesment/" + this.perEmpList.departmentId,{
                    limit: this.departAssLimit,
                    page: this.departAssPage,
                    name: this.souAssItem.name,
                    state: this.souAssItem.state,
                }).then(resp => {
                    this.departAssLists = resp.data.data.list;
                    this.departAssTotal = resp.data.data.total;
                })
            },
            selectAss(){
                this.selectAssModal = true;
                this.getDepartAss();
            },
            selected(index){
                this.myAssessmentList.aid = this.departAssLists[index].id;
                this.myAssessmentList.name = this.departAssLists[index].name;
                this.selectAssModal = false;
            },
            addMyAss(name){
                this.$refs[name].validate((valid) => {
                    if(valid){
                        this.postRequest("/empAssessment/addEmployeeAssessment",{
                            aid: this.myAssessmentList.aid,
                            data: this.myAssessmentList.data,
                            eid: this.$store.state.user.eid,
                            remark: this.myAssessmentList.remark,
                        }).then(resp => {
                            if(resp.data.error == false && resp.data.code == 200){
                                this.getMyAssessment();
                                this.$Message.success(resp.data.data);
                                this.addModal = false;
                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            getMyAssessment(){

            },
            cancel(){

            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            uploadSuccess (resp, file){
                this.myAssessmentList.data = resp.data;
                this.$Message.success("文件上传成功");
            }
        },
        mounted: function (){
            this.getPerEmp();
        },
        watch: {
            departAssPage: "getDepartAss",
        },
    }
</script>