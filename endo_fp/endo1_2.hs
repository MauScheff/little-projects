module Main where

import System.IO as IO
import qualified Data.Map as Map

main :: IO ()
main = do
  contents <- getContents
  putStr $ unlines (translate contents [])

translate :: String -> [String] -> [String]
translate "" result = result
translate string result = translate xs (result ++ [(translate' x)])
    where (x, xs) = splitAt 7 string

translate' :: String -> String
translate' key = 
    case Map.lookup key my_map of
      Just v -> v
      Nothing -> if (length key) < 7
                 then "SHORT"
                 else "UNKNOWN"

my_map = 
    Map.insert "PIPIIIC" "black" .
    Map.insert "PIPIIIP" "red" .
    Map.insert "PIPIICC" "green" .
    Map.insert "PIPIICF" "yellow" .
    Map.insert "PIPIICP" "blue" .
    Map.insert "PIPIIFC" "magenta" .
    Map.insert "PIPIIFF" "cyan" .
    Map.insert "PIPIIPC" "white" .
    Map.insert "PIPIIPF" "trans" .
    Map.insert "PIPIIPP" "opaque" .
    Map.insert "PIIPICP" "empty" .
    Map.insert "PIIIIIP" "move" .
    Map.insert "PCCCCCP" "ccw" .
    Map.insert "PFFFFFP" "cw" .
    Map.insert "PCCIFFP" "mark" .
    Map.insert "PFFICCP" "line" .
    Map.insert "PIIPIIP" "fill" .
    Map.insert "PCCPFFP" "addBitmap" .
    Map.insert "PFFPCCP" "compose" .
    Map.insert "PFFICCF" "clip" $ Map.empty
