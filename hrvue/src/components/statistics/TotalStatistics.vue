<template>
    <div>
        <Divider orientation="left" ><Icon type="ios-people" />&nbsp;&nbsp;员工统计</Divider>
        <Row>
            <Col span="2">
                <Row>
                    <Icon type="ios-folder-outline" size="60"/>
                </Row>
                <Row>
                    <h4>总档案数：14</h4>
                </Row>
            </Col>
            <Col span="2">
                <Row>
                    <Icon type="ios-people-outline" size="60"/>
                </Row>
                <Row>
                    <h4>在职员工数：14</h4>
                </Row>
            </Col>
            <Col span="2">
                <Row>
                    <Icon type="ios-exit-outline" size="60"/>
                </Row>
                <Row>
                    <h4>已离职职工数：14</h4>
                </Row>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="12">
                <div id="deparStatisticsPie" style="width:100%; height:400px;"></div>
                <div id="deparStatisticsBar" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
        <Row>
            <Col span="6">
                <div id="genderPie" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
        <Row>
            <Col span="8">
                <div id="rpPie" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
        <Divider orientation="left" ><Icon type="ios-briefcase-outline" />&nbsp;&nbsp;部门统计</Divider>
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
                        left: 'right',
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
                this.deparStatisticsBar = echarts.init(document.getElementById('deparStatisticsBar'));
                this.deparStatisticsBar.setOption({
                    title: {
                        text: '部门统计',
                        x: 'center'
                    },
                    tooltip: {  // 图列提示框，默认不显示
                        show: true,
                        color: 'red'  
                    },
                    legend: {
                        data: ['人事部', '财务部', '技术部', '策划部', '营销部']
                    },
                    xAxis: {
                        data: ['人事部', '财务部', '技术部', '策划部', '营销部']
                    },
                    series: [
                        {
                            name: '部门人数',
                            type: 'bar',
                            data: [
                                5,6,6,6,6,6
                            ],
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
