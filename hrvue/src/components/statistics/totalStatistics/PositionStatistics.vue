<template>
    <div>
        <Row>
            <Col>
                <Form :model="formItem">
                    <Row :gutter="6">
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.departmentID" placeholder="选择职位">
                                    <Option value="0">所有职位</Option>
                                    <Option value="1">经理</Option>
                                    <Option value="2">主管</Option>
                                    <Option value="3">员工</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="2">
                            <FormItem>
                                <DatePicker type="date" placeholder="选择开始日期" v-model="formItem.beforeDate"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col span="1"><p style="line-height: 35px">至</p></Col>
                        <Col span="2">
                            <FormItem>
                                <DatePicker type="date" placeholder="选择结束日期" v-model="formItem.afterDate"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col span="1">
                            <FormItem>
                                <Button icon="ios-search">查看</Button>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Col>
        </Row>
        <Row>
            <Col span="2">
                <Row>
                    <Col span="12"><h4>职位人数：</h4></Col>
                    <Col span="3"><h4>17</h4></Col>
                </Row>
                <br>
                <Row>
                    <Col span="12"><h4>平均工资：</h4></Col>
                    <Col span="3"><h4>￥1000.00</h4></Col>
                </Row>
            </Col>
            <Col span="2" offset="2">
                <Row>
                    <Col span="12"><h4>男性员工：</h4></Col>
                    <Col span="3"><h4>10</h4></Col>
                </Row>
                <br>
                <Row>
                    <Col span="12"><h4>女性员工：</h4></Col>
                    <Col span="3"><h4>7</h4></Col>
                </Row>
            </Col>
            <Col span="2" offset="2">
                <Row>
                    <Col span="12"><h4>奖励：</h4></Col>
                    <Col span="3"><h4>3</h4></Col>
                </Row>
                <br>
                <Row>
                    <Col span="12"><h4>惩罚：</h4></Col>
                    <Col span="3"><h4>8</h4></Col>
                </Row>
            </Col>
        </Row>
        <br>
        <br>
        <br>
        <Row>
            <Col span="6"  offset="1">
                <div id="positionPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="6" offset="1">
                <div id="genderPie2" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="8">
                <div id="rpPie2" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
    </div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        data() {
            return {
                positionPie: null,          //职位人数统计
                genderPie2: null,            //性别统计
                rpPie2: null,                //奖惩统计
                formItem: {
                    departmentID: '',
                    beforeDate: '',
                    afterDate: '',
                }
            }
        },
        methods: {
            drawPositionChart() {
                this.positionPie = echarts.init(document.getElementById('positionPie'));
                this.positionPie.setOption({
                    title: {
                        text: '职位统计',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['经理', '主管', '员工']
                    },
                    series: [
                        {
                            name: '部门人数',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 3, name: '经理' },
                                { value: 4, name: '主管' },
                                { value: 10, name: '员工' },
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            drawGenderChart() {
                this.genderPie2 = echarts.init(document.getElementById('genderPie2'));
                this.genderPie2.setOption({
                    title: {
                        text: '性别统计',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['男', '女']
                    },
                    series: [
                        {
                            name: '性别统计',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 3, name: '男' },
                                { value: 4, name: '女' },
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            drawRPChart() {
                this.rpPie2 = echarts.init(document.getElementById('rpPie2'));
                this.rpPie2.setOption({
                    title: {
                        text: '奖惩统计',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['奖励', '惩罚']
                    },
                    series: [
                        {
                            name: '奖惩统计',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 3, name: '奖励' },
                                { value: 8, name: '惩罚' },
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            drawCharts() {
                this.drawPositionChart();
                this.drawGenderChart();
                this.drawRPChart();
            },
        },
        mounted: function () {
            this.drawCharts();
        },
    }
</script>
