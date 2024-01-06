


  function createFormData() {
    var formData = new FormData();
    var formElements = document.getElementById('formData').querySelectorAll('input, textarea');

    formElements.forEach(function(element) {
      formData.append(element.name, element.value);
    });

    return formData;
  }

  function createJsonData() {
    var jsonData = {};
    var formElements = document.getElementById('formData').querySelectorAll('input, textarea');

    formElements.forEach(function(element) {
      jsonData[element.name] = element.value;
    });

    console.log('jsonData', jsonData)

    return jsonData;
  }

  async function fetchFormData(url, data, method= 'POST') {
    try {
      const response = await fetch(url, {
        method: method,
        body: data
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      console.log('요청이 성공했습니다.', result);
      return result;
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
      throw error;
    }
  }

  async function fetchJsonData(url, data, method = 'POST') {
    try {
      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json', // Set the appropriate content type
        },
        body: data ? JSON.stringify(data) : null,
      });

      if (!response.ok) {
        console.log('HTTP 상태 코드:', response.status);
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      console.log('전체응답:', response);
      console.log('요청이 성공했습니다.', result);
      return result;

    } catch(error) {
      console.error('There was a problem with the fetch operation:', error);
      throw error;
    }
  }

  /*
  * 비동기 호출 함수 (POST-일반)
  * @param {string} url 비동기 호출할 주소
  * @param {object} params 매개변수
  * @param {method} callbackMethod 실행될 함수
  */
  function apiFetchPost(url, params, callbackMethod) {
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
        "X-Requested-With": "XMLHttpRequest"
      },
      body: new URLSearchParams(params)
    })
        .then(res =>{
          if (res.redirected) { // 리다이렉트가 있을 경우 (에러 발생 시 화면 이동을 위해)
            window.location.href = res.url;
            res.redirect(res.url)
          }
          // 응답 데이터를 JSON 형태로 받아서 다음 then으로 넘김
          return res.json()
        })
        .then(res => {
          callbackMethod(res); // 함수 실행
        })
        .catch((error) => {
          console.log(error);
          alert("에러가 발생했습니다. \r\n관리자에게 문의해주십시오.");
        });
  }

  /*
  * 비동기 호출 함수 (POST-일반)
  * @param {string} url 비동기 호출할 주소
  * @param {object} params 매개변수
  * @param {method} callbackMethod 실행될 함수
  */
  function apiFetchPostJson(url, params, callbackMethod) {
    fetch(url, {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
      },
      body: params ? JSON.stringify(params) : null
    })
        .then(res =>{
          if (res.redirected) { // 리다이렉트가 있을 경우 (에러 발생 시 화면 이동을 위해)
            window.location.href = res.url;
            // res.redirect(res.url)
          }
          // 응답 데이터를 JSON 형태로 받아서 다음 then으로 넘김
          return res.json()
        })
        .then(res => {
           callbackMethod(res); // 함수 실행
        })
        .catch((error) => {
          console.log(error);
          // alert("에러가 발생했습니다. \r\n관리자에게 문의해주십시오.");
        });
  }