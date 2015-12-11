package swoledcademy.com.futurefrogv21;

import android.graphics.Point;
import android.util.Log;

import java.util.Map;

public class Player extends Entity
{
    private boolean running = false;

    public Player(int mapX, int mapY, int pixelWidth, int pixelHeight, int direction, String name)
    {
        super(mapX, mapY, pixelWidth, pixelHeight, direction, name);

        MapManipulator.map.get(mapY).set(mapX, '&');
    }

    @Override
    public int getBitmap()
    {
        int frameImage = R.drawable.stand_frog00;

        if(running)
        {
            if (direction == 0)
            {
                if (frameCounter == 0)
                    frameImage = R.drawable.back_run_frog00;
                else if (frameCounter >= 1) {
                    running = false;
                    frameCounter = 0;
                    return R.drawable.back_run_frog01;
                }
            }
            else if (direction == 1)
            {
                if (frameCounter == 0)
                    frameImage = R.drawable.right_run_frog00;
                else if (frameCounter >= 1) {
                    running = false;
                    frameCounter = 0;
                    return R.drawable.right_run_frog01;
                }
            }

            else if (direction == 2)
            {
                if (frameCounter == 0)
                    frameImage = R.drawable.run_frog00;
                else if (frameCounter >= 1) {
                    running = false;
                    frameCounter = 0;
                    return R.drawable.run_frog01;
                }
            }

            else if (direction == 3)
            {
                if (frameCounter == 0)
                    frameImage = R.drawable.left_run_frog00;
                else if (frameCounter >= 1) {
                    running = false;
                    frameCounter = 0;
                    return R.drawable.left_run_frog01;
                }
            }
            frameCounter++;
            return frameImage;
        }

        //NOT RUNNING
        if(direction == 0)
        {
            if(frameCounter == 0)
                frameImage = R.drawable.back_stand_frog00;
            else if(frameCounter >= 1)
            {
                frameCounter = 0;
                return R.drawable.back_stand_frog01;
            }
        }
        else if(direction == 1)
        {
            if(frameCounter == 0)
                frameImage = R.drawable.right_stand_frog00;
            else if(frameCounter >= 1)
            {
                frameCounter = 0;
                return R.drawable.right_stand_frog01;
            }
        }
        else if(direction == 2)
        {
            if (frameCounter == 0)
                frameImage = R.drawable.stand_frog00;
            else if (frameCounter == 1)
                frameImage = R.drawable.stand_frog01;
            else if (frameCounter == 2)
                frameImage = R.drawable.stand_frog02;
            else if (frameCounter == 3)
                frameImage = R.drawable.stand_frog03;
            else if (frameCounter == 4)
                frameImage = R.drawable.stand_frog04;
            else if (frameCounter >= 5) {
                frameCounter = 0;
                return R.drawable.stand_frog05;
            }
        }
        else if(direction == 3)
        {
            if(frameCounter == 0)
                frameImage = R.drawable.left_stand_frog00;
            else if(frameCounter >= 1)
            {
                frameCounter = 0;
                return R.drawable.left_stand_frog01;
            }
        }
        frameCounter++;
        return frameImage;
    }

    @Override
    public void move(int moveDirection)
    {
        Log.i("MOVEMENT", "HAHAHAHAHA");
        this.direction = moveDirection;
        try
        {
            if (moveDirection == 0) //up
            {
                if(MapManipulator.map.get(mapCoords.y - 1).get(mapCoords.x) == '@') //If the spot moved into is an entity
                {
                    Log.i("WALKED INTO SOMETHING", "WALKED INTO SOMETHING");
                    for(int i = 1; i < MapManipulator.entities.size(); i++) //Finds which entity was interacted with
                    {
                        if(MapManipulator.entities.get(i).mapCoords.x == mapCoords.x && MapManipulator.entities.get(i).mapCoords.y == mapCoords.y - 1)
                        {
                            MapManipulator.entities.get(i).interact(name); //IMPLIMENT THIS
                            break;
                        }
                    }
                }
                else if (!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y - 1).get(mapCoords.x))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.y -= 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '@');

                    running = true;
                }
                /*
                if (!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y - 1).get(mapCoords.x))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.y -= 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '&');

                    running = true;
                }
                */
            }
            else if (moveDirection == 1) //right
            {
                if(MapManipulator.map.get(mapCoords.y).get(mapCoords.x + 1) == '@') //If the spot moved into is an entity
                {
                    Log.e("WALKED INTO SOMETHING", "WALKED INTO SOMETHING");
                    for(int i = 1; i < MapManipulator.entities.size(); i++) //finds which entity was interacted with
                    {
                        if(MapManipulator.entities.get(i).mapCoords.x == mapCoords.x + 1 && MapManipulator.entities.get(i).mapCoords.y == mapCoords.y)
                        {
                            MapManipulator.entities.get(i).interact(name); //IMPLIMENT THIS
                            break;
                        }
                    }
                }
                else if (!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y).get(mapCoords.x + 1))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.x += 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '@');

                    running = true;
                }
                /*
                if (!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y).get(mapCoords.x + 1))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.x += 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '&');

                    running = true;
                }
                */
            }
            else if(moveDirection == 2) //down
            {
                if(MapManipulator.map.get(mapCoords.y + 1).get(mapCoords.x) == '@') //If the spot moved into is an entity
                {
                    Log.e("WALKED INTO SOMETHING", "WALKED INTO SOMETHING");
                    for(int i = 1; i < MapManipulator.entities.size(); i++) //finds which entity was interacted with
                    {
                        if(MapManipulator.entities.get(i).mapCoords.x == mapCoords.x && MapManipulator.entities.get(i).mapCoords.y == mapCoords.y + 1)
                        {
                            MapManipulator.entities.get(i).interact(name); //IMPLIMENT THIS
                            break;
                        }
                    }
                }
                else if(!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y + 1).get(mapCoords.x))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.y += 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '@');

                    running = true;
                }
                /*
                if(!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y + 1).get(mapCoords.x))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.y += 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '&');

                    running = true;
                }
                */
            }
            else if(moveDirection == 3) //left
            {
                if(MapManipulator.map.get(mapCoords.y).get(mapCoords.x - 1) == '@') //If the spot moved into is an entity
                {
                    Log.e("WALKED INTO SOMETHING", "WALKED INTO SOMETHING");
                    for(int i = 1; i < MapManipulator.entities.size(); i++) //finds which entity was interacted with
                    {
                        if(MapManipulator.entities.get(i).mapCoords.x == mapCoords.x - 1 && MapManipulator.entities.get(i).mapCoords.y == mapCoords.y)
                        {
                            MapManipulator.entities.get(i).interact(name); //IMPLIMENT THIS
                            break;
                        }
                    }
                }
                else if(!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y).get(mapCoords.x - 1))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.x -= 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '@');

                    running = true;
                }
                /*
                if(!(MapManipulator.noPass.contains(MapManipulator.map.get(mapCoords.y).get(mapCoords.x - 1))))
                {
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '-');
                    mapCoords.x -= 1;
                    MapManipulator.map.get(mapCoords.y).set(mapCoords.x, '&');

                    running = true;
                }
                */
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
