(function() {
  const form = document.querySelector('.needs-validation');
  form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(form);
    const login = Object.fromEntries(formData.entries());
    const path = '/auth/login';
    const request = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(login)
    };
    fetch(path, request)
      .then((response) => {
        if(response.status == 200) {
          response.json().then(authentication => {
            localStorage.setItem('token', authentication.token);
            location.assign('/home');
          });
          return;
        }
        form.reset();
        alert('Invalid Credentials');
      });
  });
})();