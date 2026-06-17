<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>SampleController 테스트</title>
  <%--<link rel="stylesheet" href="/resources/css/main.css">--%>
  <style>
    body {
      margin: 0;
      padding: 40px;
      background: #FFFDF7;
      font-family: Arial, sans-serif;
      color: #333;
    }

    .container {
      max-width: 900px;
      margin: 0 auto;
      background: white;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 4px 14px rgba(0, 0, 0, 0.08);
    }

    h1 {
      margin-top: 0;
      text-align: center;
      color: #60584C;
    }

    .desc {
      text-align: center;
      color: #666;
      margin-bottom: 30px;
    }

    .section-title {
      margin-top: 35px;
      padding-bottom: 8px;
      border-bottom: 2px solid #FFCC00;
      color: #60584C;
      font-size: 20px;
    }

    .link-list {
      list-style: none;
      padding: 0;
      margin: 15px 0 0;
    }

    .link-list li {
      margin-bottom: 12px;
    }

    .link-list a {
      display: block;
      padding: 14px 18px;
      background: #f8f9fa;
      border: 1px solid #e0e0e0;
      border-radius: 8px;
      text-decoration: none;
      color: #333;
      transition: all 0.2s ease;
    }

    .link-list a:hover {
      background: #FFCC00;
      color: #60584C;
      transform: translateX(5px);
      border-color: #FFCC00;
    }

    .url {
      display: block;
      margin-top: 5px;
      font-size: 13px;
      color: #777;
    }

    .link-list a:hover .url {
      color: #60584C;
    }

    .badge {
      display: inline-block;
      padding: 3px 8px;
      margin-right: 8px;
      border-radius: 20px;
      font-size: 12px;
      font-weight: bold;
      color: white;
      background: #60584C;
    }

    .badge.post {
      background: #4A433A;
    }

    .badge.error {
      background: #D9534F;
    }

    .upload-box {
      margin-top: 15px;
      padding: 18px;
      border: 2px dashed #FFCC00;
      border-radius: 8px;
      background: #FFFBEA;
    }

    .upload-box input {
      margin-right: 10px;
    }

    .upload-box button {
      padding: 8px 14px;
      border: none;
      border-radius: 6px;
      background: #60584C;
      color: white;
      cursor: pointer;
      transition: 0.2s;
    }

    .upload-box button:hover {
      background: #4A433A;
    }

    hr {
      border: none;
      border-top: 1px solid #eee;
      margin: 30px 0;
    }


  </style>
</head>

<body>

<div class="container">
  <h1>SampleController 테스트 화면</h1>
  <p class="desc">아래 링크를 클릭해서 요청 파라미터, ModelAttribute, Redirect, JSON, 파일 업로드를 테스트할 수 있습니다.</p>

  <h2 class="section-title">기본 요청</h2>
  <ul class="link-list">
    <li>
      <a href="/sample">
        <span class="badge">GET</span> 기본 요청
        <span class="url">/sample</span>
      </a>
    </li>

    <li>
      <a href="/sample/basic">
        <span class="badge">GET/POST</span> basic 요청
        <span class="url">/sample/basic</span>
      </a>
    </li>

    <li>
      <a href="/sample/basicOnlyGet">
        <span class="badge">GET</span> GET 전용 요청
        <span class="url">/sample/basicOnlyGet</span>
      </a>
    </li>
  </ul>

  <h2 class="section-title">파라미터 바인딩 테스트</h2>
  <ul class="link-list">
    <li>
      <a href="/sample/ex01?name=홍길동&age=20">
        <span class="badge">GET</span> DTO 자동 바인딩
        <span class="url">/sample/ex01?name=홍길동&amp;age=20</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02?name=홍길동&age=20">
        <span class="badge">GET</span> @RequestParam 바인딩
        <span class="url">/sample/ex02?name=홍길동&amp;age=20</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02-advanced">
        <span class="badge">GET</span> 기본값 파라미터 테스트
        <span class="url">/sample/ex02-advanced</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02-advanced?name=김철수&age=30">
        <span class="badge">GET</span> 선택 파라미터 전달
        <span class="url">/sample/ex02-advanced?name=김철수&amp;age=30</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02List?ids=111&ids=222&ids=333">
        <span class="badge">GET</span> ArrayList 파라미터
        <span class="url">/sample/ex02List?ids=111&amp;ids=222&amp;ids=333</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02Array?ids=AAA&ids=BBB&ids=CCC">
        <span class="badge">GET</span> 배열 파라미터
        <span class="url">/sample/ex02Array?ids=AAA&amp;ids=BBB&amp;ids=CCC</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb">
        <span class="badge">GET</span> DTO 리스트 바인딩
        <span class="url">/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb</span>
      </a>
    </li>
  </ul>

  <h2 class="section-title">날짜 / Model / Redirect 테스트</h2>
  <ul class="link-list">
    <li>
      <a href="/sample/ex03?title=test&dueDate=2023/01/01">
        <span class="badge">GET</span> 날짜 변환 테스트
        <span class="url">/sample/ex03?title=test&dueDate=2023/01/01</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex03?title=test&dueDate=2023-01-01">
        <span class="badge">GET</span> 날짜 변환 테스트 (잘못된 형식)
        <span class="url">/sample/ex03?title=test&dueDate=2023-01-01</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex04?name=홍길동&age=20&page=5">
        <span class="badge">GET</span> @ModelAttribute page 전달
        <span class="url">/sample/ex04?name=홍길동&amp;age=20&amp;page=5</span>
      </a>
    </li>


    <li>
      <a href="/sample/ex06">
        <span class="badge">GET</span> RedirectAttributes 테스트
        <span class="url">/sample/ex06</span>
      </a>
    </li>
  </ul>

  <h2 class="section-title">응답 데이터 테스트</h2>
  <ul class="link-list">
    <li>
      <a href="/sample/ex07">
        <span class="badge">GET</span> @ResponseBody JSON 객체 반환
        <span class="url">/sample/ex07</span>
      </a>
    </li>

    <li>
      <a href="/sample/ex08">
        <span class="badge">GET</span> ResponseEntity JSON 문자열 반환
        <span class="url">/sample/ex08</span>
      </a>
    </li>
  </ul>

  <h2 class="section-title">파일 업로드 테스트</h2>
  <ul class="link-list">
    <li>
      <a href="/sample/exUpload">
        <span class="badge">GET</span> 파일 업로드 화면 이동
        <span class="url">/sample/exUpload</span>
      </a>
    </li>
  </ul>

  <div class="upload-box">
    <form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
      <input type="file" name="files" multiple>
      <button type="submit">파일 업로드 요청</button>
    </form>
  </div>

  <h2 class="section-title">404 에러 테스트</h2>
  <ul class="link-list">
    <li>
      <a href="/sample/notFound">
        <span class="badge error">404</span> 존재하지 않는 요청
        <span class="url">/sample/notFound</span>
      </a>
    </li>
  </ul>
</div>

</body>
</html>