<template>
    <div>
        <Row>
            <Col span="22">
                <Row :gutter="6">
                    <Col span="2">
                        <Select v-model="souFormItem.state" placeholder="考核状态" clearable>
                            <Option value="已开始">已开始</Option>
                            <Option value="已结束">已结束</Option>
                        </Select>
                    </Col>
                    <Col span="3">
                        <Input v-model="souFormItem.name" clearable placeholder="请输入考核名称"/>
                    </Col>
                    <Col span="1">
                        <Button icon="ios-search" @click="getDepartAss" >搜索</Button>
                    </Col>
                </Row>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" :columns="departAssColumns" :data="departAssLists">
                <template slot-scope="{ row, index }" slot="commit">
                    <p v-if="row.commit">已提交</p>
                    <p v-else>未提交</p>
                </template>
                <template slot-scope="{ row, index }" slot="action">
                    <Button type="primary" style="margin-right: 5px" @click="beforeMyAss(index)" v-if="row.commit && row.state == '已开始'">编辑</Button>
                    <Button type="primary" style="margin-right: 5px" @click="getMyAss(index)" v-else-if="row.commit && row.state == '已结束'">查看</Button>
                    <Button type="primary" style="margin-right: 5px" v-else-if="row.state == '已结束'" disabled>未考核</Button>
                    <Button type="primary" style="margin-right: 5px" @click="addAss(index)" v-else-if="row.state == '已开始'">提交</Button>
                </template>
            </Table>
            <Row type="flex" justify="center"  :style="{margin: '10px 0 0 0'}">
                <Page :total="departAssTotal" :page-size="departAssPageSize" show-elevator show-total @on-change="departAssPageChange"/>            
            </Row>
        </Row>
        <Modal
            v-model="addModal"
            title="提交考核"
            @on-visible-change="cancel">
            <Row>
                <Col span="22">
                    <Form :model="myAssessmentList" :rules="rules" :label-width="100" ref="myAssessmentList">
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
            v-model="showMyAssModal"
            title="查看我的考核"
            width=40%>
            
            <Form :model="showMyAss" :label-width="100" ref="showMyAss">
                <FormItem label="考核项目:">
                    {{showMyAss.name}}
                </FormItem>
                <FormItem label="附件:">
                    {{showMyAss.data}}
                </FormItem>
                <FormItem label="说明:">
                    {{showMyAss.remark}}
                </FormItem>
                <FormItem label="是否查阅:">
                    {{showMyAss.check}}
                </FormItem>
                <FormItem label="审核结果:">
                    {{showMyAss.result}}
                </FormItem>
            </Form>
            <div slot="footer">
                <Button @click="selectAssModal=false">返回</Button>
            </div>
        </Modal>
        <Modal
            v-model="updateMyAssModal"
            title="编辑我的考核">
            
            <Form :model="updateMyAss" :label-width="100" ref="updateMyAss" :rules="rules" >
                <FormItem label="考核项目:">
                    {{updateMyAss.name}}
                </FormItem>
                <FormItem label="附件:">
                    <a :href="updateMyAss.data"  target="_blank" download>{{updateMyAss.data|formatData}}</a>
                    <Upload
                        ref="upload"
                        :on-success="updateUploadSuccess"
                        name="picture"
                        action="http://111.230.141.100:8080/hrserver/empAssessment/data">
                        <Button icon="ios-cloud-upload-outline">上传新文件</Button>
                    </Upload>
                </FormItem>
                <FormItem label="说明:" prop="remark">
                    <Input v-model="updateMyAss.remark" type="textarea" placeholder="请输入考核说明"></Input>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="primary" @click="updateMyAssessment('updateMyAss')">保存</Button>
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
                updateMyAssModal: false,
                showMyAssModal: false,
                departAssPage: 1,
                departAssTotal: 100,
                departAssLimit: 5,
                departAssPageSize: 5,
                page:1,
                total: 100,
                limit: 10,
                perEmpList: {},
                departAssLists: [],
                showMyAss: {},
                updateMyAss: {},
                showMyAssName: '',
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
                    {
                        title: '操作',
                        slot: 'action',
                        width: 120,
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
            getPerEmp(){
                this.getRequest("/employee/myfile").then(resp=>{
                    this.perEmpList = resp.data.data;
                })
            },
            getDepartAss() {
                this.getRequest("/empAssessment/myAssessment",{
                    limit: this.departAssLimit,
                    page: this.departAssPage,
                    name: this.souFormItem.name,
                    state: this.souFormItem.state,
                }).then(resp => {
                    this.departAssLists = resp.data.data.list;
                    this.departAssTotal = resp.data.data.total;
                })
            },
            addAss(index){
                this.addModal = true;
                this.myAssessmentList.aid = this.departAssLists[index].id;
                this.myAssessmentList.name = this.departAssLists[index].name;
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
                                this.getDepartAss();
                                this.$Message.success(resp.data.data);
                                this.addModal = false;

                                //初始化
                                this.$refs.upload.clearFiles();
                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            getMyAss(index){
                this.getRequest("/empAssessment/myAssessment/" + this.departAssLists[index].id).then(resp => {
                    this.showMyAss = resp.data.data;
                });
                this.showMyAssModal = true;
            },
            beforeMyAss(index){
                this.getRequest("/empAssessment/myAssessment/" + this.departAssLists[index].id).then(resp => {
                    console.log(resp);
                    this.updateMyAss = resp.data.data;
                });
                this.updateMyAssModal = true;
            },
            updateMyAssessment(name){
                this.$refs[name].validate((valid) => {
                    if(valid){
                        this.postRequest("/empAssessment/updateEmployeeAssessment",{
                            id: this.updateMyAss.id,
                            data: this.updateMyAss.data,
                            remark: this.updateMyAss.remark
                        }).then(resp => {
                            if(resp.data.error == false && resp.data.code == 200){
                                this.getDepartAss();
                                this.$Message.success(resp.data.data);
                                this.updateMyAssModal = false;

                                //初始化
                                this.$refs.upload.clearFiles();
                                this.$refs[name].resetFields();
                            } else {
                                this.$Message.error(resp.data.message);
                            }
                        })
                    }
                })
            },
            cancel(){

            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            uploadSuccess (resp, file){
                this.myAssessmentList.data = resp.data;
                this.$Message.success("文件上传成功");
            },
            updateUploadSuccess(resp, file){
                this.updateMyAss.data = resp.data;
                this.$Message.success("文件上传成功");
            },
        },
        mounted: function (){
            this.getPerEmp();
            this.getDepartAss();
        },
        watch: {
            departAssPage: "getDepartAss",
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