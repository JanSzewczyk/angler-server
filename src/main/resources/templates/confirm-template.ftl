<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Confirm Your E-Mail</title>

    <link
            href="https://fonts.googleapis.com/css?family=Lato:400,700&display=swap"
            rel="stylesheet"
    />

    <style>
        body {
            font-family: "Lato", sans-serif;
            font-weight: 400;
            font-size: 15px;
            line-height: 1.8;
            color: rgba(0, 0, 0, 0.4);
        }

        .bg_green {
            background: #208b4a;
        }
        .bg_white {
            background: #ffffff;
        }

        .bg_black {
            background: #000000;
        }
        .bg_dark {
            background: rgba(0, 0, 0, 0.8);
        }
        .email-section {
            padding: 2.5em;
        }

        a {
            color: #f5564e;
        }

        /* LOGO */
        .logo h1 {
            margin: 0;
        }

        .logo h1 a {
            color: #000;
            font-size: 20px;
            font-weight: 700;
            text-transform: uppercase;
            font-family: "Lato", sans-serif;
        }

        .navigation {
            padding: 0;
        }

        .navigation li {
            list-style: none;
            display: inline-block;
            margin-left: 5px;
            font-size: 12px;
            font-weight: 700;
            text-transform: uppercase;
        }

        .navigation li a {
            color: rgba(0, 0, 0, 0.6);
        }

        /*HERO*/
        .hero {
            position: relative;
            z-index: 0;
        }

        .hero .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            content: "";
            width: 100%;
            background: #000000;
            z-index: -1;
            opacity: 0.3;
        }

        .hero .icon {
        }

        .hero .icon a {
            display: block;
            width: 60px;
            margin: 0 auto;
        }

        .hero .text {
            color: rgba(255, 255, 255, 0.8);
            padding: 0 4em;
        }

        .hero .text h2 {
            font-family: "Lato", sans-serif;
            color: #ffffff;
            font-size: 40px;
            margin-bottom: 0;
            line-height: 1.2;
            font-weight: 900;
        }

        /*HEADING SECTION*/
        .heading-section{
        }

        .heading-section h2{
            font-family: "Lato", sans-serif;
            color: #000000;
            font-size: 24px;
            margin-top: 0;
            line-height: 1.4;
            font-weight: 700;
        }

        .heading-section-white{
            color: rgba(255,255,255,.8);
        }

        .heading-section-white h2{
            font-family: "Lato", sans-serif;
            line-height: 1;
            padding-bottom: 0;
        }

        .heading-section-white h2{
            font-family: "Lato", sans-serif;
            color: #ffffff;
        }

        .icon img{
        }

        /*HEADING SECTION*/
        .heading-section{
        }
        .heading-section h2{
            color: #000000;
            font-size: 24px;
            margin-top: 0;
            line-height: 1.4;
            font-weight: 700;
        }

        .heading-section-white{
            color: rgba(255,255,255,.8);
        }
        .heading-section-white h2{
            font-family: "Lato", sans-serif;
            line-height: 1;
            padding-bottom: 0;
        }
        .heading-section-white h2{
            color: #ffffff;
        }
        .heading-section-white .subheading{
            margin-bottom: 0;
            display: inline-block;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 2px;
            color: rgba(255,255,255,.4);
        }
        .icon{
            text-align: center;
        }
        .icon img{
        }

        /* Button */
        .button {
            margin-left: auto;
            margin-right: auto;
            font-family: "Lato", sans-serif;
            width: 80%;
            border: 2px solid #27ae60;
            border-radius: 8px;
            font-weight: 500;
            background: none;
            padding: 0.7rem 1rem;
            font-size: 20px;
            text-decoration: none;
            cursor: pointer;
            text-align: center;
            transition: 0.8s;
            position: relative;
            overflow: hidden;
        }
        .button:disabled {
            cursor: default;
        }
        .button::before {
            content: "";
            position: absolute;
            left: 0;
            width: 100%;
            height: 0%;
            background: #27ae60;
            z-index: -1;
            transition: 0.8s;
        }
        .btn1 {
            z-index: 3;
            color: #27ae60;
        }
        .btn1:hover {
            z-index: 3;
            color: #27ae60;
        }
        .btn1::before {
            top: 0;
            border-radius: 0 0 50% 50%;
        }
        .btn1::before {
            height: 200%;
        }
        .btn1:hover::before {
            height: 0%;
        }

        /*FOOTER*/
        .footer{
            color: rgba(255,255,255,.5);
        }
        .footer .heading{
            color: #ffffff;
            font-size: 20px;
        }
        .footer ul{
            margin: 0;
            padding: 0;
        }
        .footer ul li{
            list-style: none;
            margin-bottom: 10px;
        }
        .footer ul li a{
            color: rgba(255,255,255,1);
        }
    </style>

</head>
<body
        width="100%" style="margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;"
>
<center style="width: 100%; background-color: #f1f1f1;">
    <div style="max-width: 600px; margin: 0 auto;" class="email-container">
        <!-- BEGIN BODY -->
        <table
                align="center"
                role="presentation"
                cellspacing="0"
                cellpadding="0"
                border="0"
                width="100%"
                style="margin: auto;"
        >
            <tr>
                <td valign="top" class="bg_green" style="padding: 1em 2.5em;">
                    <table
                            role="presentation"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            width="100%"
                    >
                        <tr>
                            <td width="40%" class="logo" style="text-align: left;">
                                <a href="localhost:3000/"><img src="https://i.ibb.co/jWCk9Km/full-logo.png" alt="full-logo" border="0" height="40"></a>
                            <td width="60%" class="logo" style="text-align: right;">
                                <ul class="navigation">
                                    <li><a href="localhost:3000/">Home</a></li>
                                    <li><a href="localhost:3000/">About</a></li>
                                    <!-- <li><a href="#">Works</a></li>
                                    <li><a href="#">Blog</a></li>
                                    <li><a href="#">Contact</a></li> -->
                                </ul>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!-- end tr src="https://i.ibb.co/cwz32dQ/cnf.jpg" alt="cnf"  -->
            <tr>
                <td
                        valign="middle"
                        class="hero bg_white"
                        style="background-image: url(https://i.ibb.co/cwz32dQ/cnf.jpg); background-size: cover; height: 400px;"
                >
                    <div class="overlay"></div>
                    <table>
                        <tr>
                            <td>
                                <div class="text" style="text-align: center;">
                                    <h2>
                                        Congratulations.</br> You joined our community
                                    </h2>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!-- end tr -->
            <tr>
                <td class="bg_dark email-section" style="text-align:center;">
                    <div class="heading-section heading-section-white">
                        <h2>Hello ${Name}</h2>
                        <p>
                            To log in to our application you must first follow the instructions below.
                        </p>
                    </div>
                </td>
            </tr>
            <!-- end: tr -->
            <tr>
                <td class="bg_white">
                    <table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%">
                        <tr>
                            <td class="bg_white">
                                <table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%"  style="text-align:center">
                                    <tr>
                                        <td class="bg_white email-section" style="align-items: center;">
                                            <div class="heading-section" style="text-align: center; padding: 0 30px;">
                                                <p> To complete the registration process, click the button below: </p>
                                            </div>
                                            <a href="localhost:3000/confirm?email=${Email}" style="text-decoration: none;">
                                                <div class="button btn1">CONFIRM YOUR EMAIL ADDRESS</div>
                                            </a>
                                            <div style="vertical-align:top;width:80%;max-width:600px;margin:0 auto 16px auto;border-bottom-width:2px;border-style:solid;border-bottom-color:#27ae60;border-top:none;border-right:none;border-left:none;height:9.5px;padding: 16px 8px 8px 8px;"></div>
                                            <div class="heading-section" style="text-align: center; padding: 0 30px;">
                                                <p> Or go to the link below:</p>
                                            </div>
                                            <a href="localhost:3000/confirm?email=${Email}" style="color: rgba(0, 0, 0, 0.8);margin: 0 auto;;">
                                                localhost:3000/confirm?email=${Email}
                                            </a>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
            <!-- end: tr -->
        </table>
        <table align="center" role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%" style="margin: auto;">
            <tr>
                <td valign="middle" class="bg_black footer email-section">
                    <table>
                        <tr>
                            <td valign="top" width="33.333%">
                                <table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%">
                                    <tr>
                                        <td style="text-align: left; padding-right: 10px;">
                                            <p style="color: rgba(255,255,255,.8);;">&copy; 2019-2020 Angler. All Rights Reserved</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</center>
</body>
</html>
