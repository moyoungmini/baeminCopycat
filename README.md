# Beamin Copycat

3rd party API
- Use Kakao Map
- Use Kakao Keyword Rest API

collaboration Server
- Use Api created by server developer to use OKHttp and Passing JSON

Android
- Navigation Bar
- Advertisement auto scrool to use ViewPager
- Return Fragment that position to use tablayout and viewpager
- When print listview to read information of server
- Read Store List information and that Store menu and information
- Else Exception Handling


-----------------------------------------------------------
메인화면
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069916-e8a71380-3520-11e9-90ac-ec7c5d098f4a.jpg"></div>
  <br>
- 네비게이션바 or 지도검색화면 or 가게리스트 화면으로 이동할 수 있습니다. <br>
- 광고배너 이미지를 서버에서 읽어와 Glide 라이브러리를 통해 이미지를 갔고왔고 Thread를 이용하여 자동스크롤 되게 하였고 광고가 끝까지 갈 경우 자동으로 처음으로 광고화면이 넘어가도록 설정을 하였습니다.<br>
- 화면 중간에 있는 9개의 메뉴 중 하나를 선택하면 해당 메뉴가 Tablayout의 초기값인 가게리스트 화면으로 이동합니다.
</div>
<br>
<br>
<br>
네비게이션바
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069919-ed6bc780-3520-11e9-8ff5-d7537e828137.jpg"></div>
- 로그인 화면으로 넘어갈 수 있으며 이 App의 특징을 적어논 item들을 배치하였다.
</div>
<br>
<br>
<br>
로그인
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069929-f361a880-3520-11e9-8a76-a047f0c98688.jpg"></div>
- 회원가입 화면으로 이동할 수 있으며, 로그인에 성공할 경우 jwt 토큰을 저장하고 메인화면으로 이동을 한다.
- EditText의 Focus를 둘 때 EditText의 밑줄의 색을 바꾸게 하였고 이메일 형식에 대한 validation을 client측에서 설정을 하였다.
</div>
<br>
<br>
<br>
핸드폰 회원가입
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069942-fbb9e380-3520-11e9-93da-7a2beb6e6758.jpg"></div>
- 회원가입 첫 페이지로 서버와 통신을 하여 핸드폰 인증 유무에 따라 완료버튼의 활성화를 결정 짓게하였다. 
</div>
<br>
<br>
<br>
회원가입
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069944-007e9780-3521-11e9-80f1-ed9be3cb690a.jpg"></div>
</div>
<br>
<br>
<br>
지도검색
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069953-083e3c00-3521-11e9-9909-66b27a91bc08.jpg"></div>
- Kakao Keyword 3Party API를 이용하여 사용자가 입력을 하면 그에 해당하는 리스트들을 출력하여 보여주게 했다. 이때 리스트 안의 아이템을 클릭 할 경우 그에 해당하는 위/경도를 갖고와 상세주소 페이지로 이동을 한다.
- 현 위치주소를 클릭 할 경우 사용자의 현 위치에 대한 권한을 동의한다는 알림이 나오고 이 알림에 동의 할 경우 현 위치의 위/경도를 갖고와 상세주소 페이지로 이동을 한다.
</div>
<br>
<br>
<br>
상세주소
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069957-0aa09600-3521-11e9-9991-ca8c31ad1089.jpg"></div>
- 지도에서 Touch로 marker의 위치를 바꿀 경우 marker의 주소를 바로바로 바꾸게 하였다.
- 완료버튼을 클릭 할 경우 사용자의 바뀐 위치가 저장되게 구현하였다.
</div>
<br>
<br>
<br>
가게리스트
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069972-1429fe00-3521-11e9-94bf-02f1f8bc7267.jpg"></div>
</div>
<br>
<br>
<br>
가게리스트
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069977-1724ee80-3521-11e9-89b3-6b86280ecc20.jpg"></div>
- 메인화면에서 선택한 메뉴를 초기값으로한 Tablayout에서 서버와 통신하여 해당 메뉴에 대한 정보를 Item으로하여 Listview에 출력합니다.
- 구현방식은 Tablayout 아래 Viewpager가 있고 이때 여러개의 Fragment를 두게 구현하여 Tablayout 선택 or Touch로 이동을 하는 방식으로 가게 리스트를 불러오게 구현하였습니다.
</div>
<br>
<br>
<br>
가게메뉴정보
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069983-1ab87580-3521-11e9-8ea4-fe304763772b.jpg"></div>
- 가게리스트 화면에서 가게에 대한 아이템을 클릭 할 경우 해당 서버와 통신하여 가게에 대한 정보가 보여집니다. 이때 가게에 대한 메뉴 혹은 정보가 리스트뷰로써 보여집니다.
</div>
<br>
<br>
<br>
전화가기버튼 클릭 할 경우
<div>
<div><img width="200" src="https://user-images.githubusercontent.com/47071342/53069994-1e4bfc80-3521-11e9-8d8b-426bd0caec96.jpg"></div>
- 가게 메뉴 정보 화면에서 전화가기 버튼을 클릭 할 경우의 전화거는 Intent로 이동을 합니다.
</div>
