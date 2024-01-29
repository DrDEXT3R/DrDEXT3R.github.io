---
title: "Contact"
permalink: /contact/
browser_title: Contact \| Tomasz Strzoda
header:
    image: "/assets/images/contact/contact.jpg"
---

If you want to send me feedback or just contact me, you can do it here.

<form action="https://formsubmit.co/el/huzepo" method="post">
    <!-- <input type="text" name="_honey" style="display:none"> -->
    <!-- <input type="hidden" name="_next" value="https://tomaszstrzoda.com/contact/thankyou/"> -->
    <!-- <input type="hidden" name="_captcha" value="false"> -->
    <!-- <input type="hidden" name="_subject" value="New submission!"> -->
    <label for="fullname">Full Name</label>
    <input type="text" id="fullname" name="name" required>
    <label for="emailAddress">Email Address</label>
    <input type="email" id="emailAddress" name="email" required>
    <label for="subject">Subject</label>
    <input type="text" id="subject" name="subject">
    <label for="message">Message</label>
    <textarea rows="5" id="message" name="message" required></textarea>
    <button type="submit">Send</button>  
</form>
