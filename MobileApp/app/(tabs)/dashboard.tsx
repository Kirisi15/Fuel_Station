import React from "react";
import { View, Text, Pressable, ImageBackground, StyleSheet } from "react-native";
import { useRouter } from "expo-router";

export default function DashboardScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <ImageBackground 
        source={require("../../assets/images/background-image.jpg")} 
        style={styles.background} 
        resizeMode="cover"  
      >
        <View style={styles.overlay}>
          <Text style={styles.title}>Welcome to Fuel Station</Text>

          <Pressable style={styles.button} onPress={() => router.push("./signin.tsx")}>
            <Text style={styles.buttonText}>Sign In</Text>
          </Pressable>
        </View>
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  background: {
    flex: 1,
    width: "100%",
    height: "100%",
    justifyContent: "center",
    alignItems: "center",
  },
  overlay: {
    backgroundColor: "rgba(0, 0, 0, 0.6)", 
    padding: 20,
    borderRadius: 10,
    alignItems: "center",
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    color: "#fff",
    marginBottom: 20,
  },
  button: {
    backgroundColor: "#ff9800",
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 5,
    marginTop: 10,
  },
  buttonText: {
    fontSize: 18,
    color: "#fff",
  },
});


