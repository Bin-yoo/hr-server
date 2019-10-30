<template>
    <div>
        <Row>
            <Col>
                <Form :model="formItem">
                    <Row :gutter="6">
                        <Col span="2">
                            <FormItem>
                                <Select v-model="formItem.departmentID" placeholder="选择部门">
                                    <Option value="0">所有部门</Option>
                                    <Option value="1">财务部</Option>
                                    <Option value="2">技术部</Option>
                                    <Option value="3">人事部</Option>
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
                    <Col span="12"><h4>部门人数：</h4></Col>
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
                <div id="deparStatisticsPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="6" offset="1">
                <div id="genderPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="8">
                <div id="rpPie" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
    </div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        data() {
            return {
                deparStatisticsPie: null,   //部门人数统计
                genderPie: null,            //性别统计
                rpPie: null,                //奖惩统计
                formItem: {
                    departmentID: '',
                    beforeDate: '',
                    afterDate: '',
                }
            }
        },
        methods: {
            drawDeparChart() {
                this.deparStatisticsPie = echarts.init(document.getElementById('deparStatisticsPie'));
                this.deparStatisticsPie.setOption({
                    title: {
                        text: '部门统计',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['人事部', '财务部', '技术部', '策划部', '营销部']
                    },
                    series: [
                        {
                            name: '部门人数',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 3, name: '人事部' },
                                { value: 4, name: '财务部' },
                                { value: 4, name: '技术部' },
                                { value: 3, name: '策划部' },
                                { value: 2, name: '营销部' }
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
                this.genderPie = echarts.init(document.getElementById('genderPie'));
                this.genderPie.setOption({
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
                this.rpPie = echarts.init(document.getElementById('rpPie'));
                this.rpPie.setOption({
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
                this.drawDeparChart();
                this.drawGenderChart();
                this.drawRPChart();
            },
        },
        mounted: function () {
            this.drawCharts();
        },
    }
</script>
