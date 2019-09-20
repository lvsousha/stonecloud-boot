var app = new Vue({
  el: '#app',
  data: {
    tasks: [{
      id: "id1",
      name: "name1"
    }, {
      id: "id2",
      name: "name2"
    }],
    processInstances:[]
  },
  methods: {
    start: function () {
      axios.post('process/start')
      .then(function (response) {
        app.list();;
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    audit: function (taskId, operate) {
      axios.post('process/audit', {
        taskId: taskId,
        operate: operate
      })
      .then(function (response) {
        app.list();
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    list: function () {
      axios.post('process/list')
      .then(function (response) {
        app.tasks = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    showPng: function(){
      window.open("process/showPng");
    },
    history: function () {
      axios.post('process/history')
      .then(function (response) {
        app.processInstances = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
    }
  }
});
app.list();
app.history();
