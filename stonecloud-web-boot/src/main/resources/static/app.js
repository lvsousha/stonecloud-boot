var app = new Vue({
  el: '#app',
  data: {
    tasks: [{
      id: "id1",
      name: "name1"
    }, {
      id: "id2",
      name: "name2"
    }]
  },
  methods: {
    test: function () {
      axios.post('process/test', {
        id: 'Fred'
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    start: function () {
      axios.post('process/start')
      .then(function (response) {
        list();
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
    }
  }
});
app.list();
