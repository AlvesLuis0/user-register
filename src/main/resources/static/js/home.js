(function() {
  const output = document.getElementById('output');
  const path = '/user/ping';
  const request = {
    method: 'GET',
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  };
  fetch(path, request)
    .then(response => {
      if(response.status == 200) {
        output.innerText = 'You are logged :)';
        return;
      }
      location.assign('/login');
    });
})();