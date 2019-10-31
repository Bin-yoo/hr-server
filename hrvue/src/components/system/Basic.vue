<template>
    <Tabs value="name1">
        <TabPane label="部门管理" name="name1">
            <dep-mana></dep-mana>
        </TabPane>
        <TabPane label="职称管理" name="name2">
            <joblvl-mana></joblvl-mana>
        </TabPane>
        <TabPane label="职位管理" name="name3">
            <pos-mana></pos-mana>
        </TabPane>
    </Tabs>
</template>
<script>
    import DepMana from './basic/DepMana.vue'
    import JobLvlMana from './basic/JobLvlMana.vue'
    import PosMana from './basic/PosMana.vue'
    export default {
    data() {
        return {
            
        };
    },
    methods: {},
    components: {
        'dep-mana': DepMana,
        'joblvl-mana': JobLvlMana,
        'pos-mana': PosMana
    }
    };
</script>
