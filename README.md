# Vanilla Sword Blocking

This is a very simple plugin that **brings back the old sword blocking mechanichs**, allowing You to block with your sword to reduce taken damage by 50%. <br>
The best part about it: It's **server-only**, so Players don't even need to install a mod on their client for this - just add the plugin to the server and it works for everyone!

![An image showing a Netherite sword being blocked, like in 1.8](https://github.com/user-attachments/assets/f3cc8477-04a8-4bb3-882c-451bbeee422b)

> [!IMPORTANT]
> While this mod can be installed on any server version 1.21.2 and later, the blocking will only be displayed correctly by clients in version 24w44a (1.21.4) and later.

<details>
  
<summary>How it works</summary>

  Essentialy, this plugin uses the recently added component called "consumable", to make Minecraft think you can eat swords!
  
  This is used to enable an animation called "block", so it looks like you're blocking.
  
  Then, the plugin prevents you from actually eating your sword, and instead reduces damage when it detects you are blocking ("eating", from minecraft's perspective) your sword.
</details>
