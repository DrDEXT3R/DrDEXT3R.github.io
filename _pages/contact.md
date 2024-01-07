---
title: "Contact"
permalink: /contact/
browser_title: Contact \| Tomasz Strzoda
header:
    image: "/assets/images/contact/contact.jpg"
---

If you want to send me feedback or just contact me, you can do it here.

<script type="text/javascript">var submitted=false;</script>
<iframe name="hidden_iframe" id="hidden_iframe" style="display:none;" 
onload="if(submitted) {window.location='thankyou';}"></iframe>

<script src="https://www.google.com/recaptcha/api.js"></script>
<form action="https://smartforms.dev/submit/5e13017bd2b1f304f056347b" method="post" target="hidden_iframe" onsubmit="submitted=true;">
    <label>Name</label>
    <input type="text" name="name" required>
    <label>Email Address</label>
    <input type="email" name="email" required>
    <label>Subject</label>
    <input type="text" name="subject">
    <label>Message</label>
    <textarea rows="5" name="message" required></textarea>
    <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6Lf_v8wUAAAAAPiQ09yyVlLGFiS1jPGRWOv-zfbB" required></div>
    <button id="submitBtn" style="margin-top:10px;" type="submit" disabled>Send</button>    
</form>