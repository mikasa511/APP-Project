import { Avatar, Box, IconButton, Tooltip } from "@mui/material";
import React from "react";
import Card from "../../../ui/Card/Card";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import { IconButtonStyled } from "./ImageCard.style.js";

const ImageCard = ({
  thumbnailSrc = "",
  isLiked = false,
  isBookmarked = false,
  noOfLikes = 0,
  photographerName = "",
  imageBgColor = "#444",
  showOnlyImage = false,
  sxCard = {},
}) => {
  return (
    <Card
      sx={{
        display: "flex",
        flexDirection: "column",
        width: "100%",
        height: "300px",
        // padding: "5px",
        gap: "5px",
        overflow: "none",
        ...sxCard,
      }}
    >
      <Box
        sx={{
          flex: 1,
          minHeight: "0px",
          width: "auto",
          backgroundColor: imageBgColor,
          borderRadius: "10px",
          position: "relative",
          overflow: "none",
          zIndex: 1,
          transition: "100ms all ease-in-out",
          "&:hover  .overlay": {
            display: "flex",
          },
        }}
      >
        <Box
          component="img"
          src={thumbnailSrc}
          sx={{ height: "100%", width: "100%", objectFit: "contain" }}
        ></Box>
        <Box
          className="overlay"
          sx={{
            // display: "flex",
            display: "none",
            padding: "10px",
            justifyContent: "flex-end",
            alignItems: "flex-end",
            gap: "10px",
            position: "absolute",
            bottom: 0,
            height: "200px",
            transition: "100ms all ease-in-out",
            width: "100%",
            background:
              "-webkit-gradient(linear, left top, left bottom, color-stop(62%, transparent), color-stop(63.94%, rgba(0,0,0,0.00345888)), color-stop(65.89%, rgba(0,0,0,0.014204)), color-stop(67.83%, rgba(0,0,0,0.0326639)), color-stop(69.78%, rgba(0,0,0,0.0589645)), color-stop(71.72%, rgba(0,0,0,0.0927099)), color-stop(73.67%, rgba(0,0,0,0.132754)), color-stop(75.61%, rgba(0,0,0,0.177076)), color-stop(77.56%, rgba(0,0,0,0.222924)), color-stop(79.5%, rgba(0,0,0,0.267246)), color-stop(81.44%, rgba(0,0,0,0.30729)), color-stop(83.39%, rgba(0,0,0,0.341035)), color-stop(85.33%, rgba(0,0,0,0.367336)), color-stop(87.28%, rgba(0,0,0,0.385796)), color-stop(89.22%, rgba(0,0,0,0.396541)), color-stop(91.17%, rgba(0,0,0,0.4)))",
            borderRadius: "0px 0px 10px 10px",
          }}
        >
          {" "}
          <Tooltip
            disableInteractive
            title={isBookmarked ? "Remove from Favourites" : "Collect"}
          >
            <IconButtonStyled>
              {isBookmarked ? (
                <BookmarkIcon
                  sx={{ color: (theme) => theme.pallete.primary }}
                />
              ) : (
                <BookmarkBorderIcon />
              )}
            </IconButtonStyled>
          </Tooltip>
          <Tooltip
            disableInteractive
            title={isLiked ? "You liked this. Click to undo" : "Like"}
          >
            <IconButtonStyled>
              {isLiked ? (
                <FavoriteIcon
                  sx={{ color: (theme) => theme.pallete.colors.pink }}
                />
              ) : (
                <FavoriteBorderIcon />
              )}
            </IconButtonStyled>
          </Tooltip>
        </Box>
      </Box>
      {!showOnlyImage && (
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            height: "auto",
            backgroundColor: "transparent",
            // color: "#fff",
          }}
        >
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: "5px",
            }}
          >
            <Avatar sx={{ width: 24, height: 24, fontSize: "0.8rem" }}>
              {photographerName.charAt(0)}
            </Avatar>
            <Box sx={{ fontSize: "1rem" }}>{photographerName}</Box>
          </Box>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: "5px",
            }}
          >
            <Tooltip title="No of Likes">
              <FavoriteIcon
                fontSize="10px"
                sx={{ color: (theme) => isLiked && theme.pallete.colors.pink }}
              />
            </Tooltip>
            <div>{noOfLikes}</div>
          </Box>
        </Box>
      )}
    </Card>
  );
};

export default ImageCard;